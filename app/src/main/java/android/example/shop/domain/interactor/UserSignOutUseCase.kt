package android.example.shop.domain.interactor

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserSignOutUseCase @Inject constructor(

) {
    operator fun invoke() {
        FirebaseAuth.getInstance().signOut()
    }
}