package android.example.shop.domain.interactor

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class IsUserSignedUseCase @Inject constructor(
) {
    operator fun invoke(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }
}