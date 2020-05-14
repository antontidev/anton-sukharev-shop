package android.example.shop.domain.interactor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetUserUseCase @Inject constructor(

) {
    private val user = FirebaseAuth.getInstance().currentUser

    operator fun invoke(): FirebaseUser {
        FirebaseAuth.getInstance().currentUser
        return user!!
    }
}