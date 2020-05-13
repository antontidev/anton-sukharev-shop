package android.example.shop.domain.interactor

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class GetUserUseCase @Inject constructor(

) {
    private val user = FirebaseAuth.getInstance().currentUser

    operator fun invoke(): String {
        return user?.displayName ?: ""
    }
}