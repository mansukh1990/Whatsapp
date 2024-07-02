import com.example.whatsapp.domain.repository.AuthRepository
import com.example.whatsapp.presentation.MainActivity
import com.example.whatsapp.utils.Resource
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl @Inject constructor(
    var firebaseAuth: FirebaseAuth,
    var firebaseFirestore: FirebaseFirestore,

    ) : AuthRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun phoneNumberSignIn(
        phoneNumber: String,
        activity: MainActivity,
    ): Flow<Resource<Boolean>> = channelFlow {
        try {
            trySend(Resource.Loading).isSuccess
            val options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber)
                .setActivity(activity)
                .setTimeout(60, TimeUnit.SECONDS)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        coroutineScope.launch {
                            signInWithAthCredential(p0)
                        }
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        coroutineScope.launch {
                            trySend(
                                Resource.Error(
                                    p0.localizedMessage ?: "An Error Occurred"
                                )
                            ).isSuccess
                        }
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        p1: PhoneAuthProvider.ForceResendingToken,
                    ) {
                        coroutineScope.launch {
                            withContext(Dispatchers.Main) {
                                activity.showBottomSheet()
                            }
                            activity.optValue.collect {
                                if (it.isNotBlank()) {
                                    trySend(
                                    signInWithAthCredential(
                                        PhoneAuthProvider.getCredential(
                                            verificationId,
                                            it
                                        )
                                    )).isSuccess
                                }
                            }

                        }
                    }

                }).build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            awaitClose()
        } catch (exception: Exception) {
            Resource.Error(exception.localizedMessage ?: "An Error Occurred")
        }
    }

    override fun isUserAuthenticated(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun getUserId(): String {
        return firebaseAuth.currentUser?.let {
            it.uid
        } ?: ""
    }

    override suspend fun signInWithAthCredential(phoneAuthCredential: PhoneAuthCredential): Resource<Boolean> =
        suspendCoroutine { continuation ->
            firebaseAuth.signInWithCredential(phoneAuthCredential)
                .addOnSuccessListener {
                    continuation.resume(Resource.Success(true))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(
                        Resource.Error(
                            exception.localizedMessage ?: "An Error Occurred"
                        )
                    )
                }

        }
}