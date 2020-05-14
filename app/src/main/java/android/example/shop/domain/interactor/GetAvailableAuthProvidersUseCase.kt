package android.example.shop.domain.interactor

import com.firebase.ui.auth.AuthUI
import javax.inject.Inject

class GetAvailableAuthProvidersUseCase @Inject constructor(

) {
    operator fun invoke(): List<AuthUI.IdpConfig> {
        return listOf(AuthUI.IdpConfig.GoogleBuilder().build())
    }
}