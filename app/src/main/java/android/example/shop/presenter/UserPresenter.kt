package android.example.shop.presenter

import android.example.shop.domain.interactor.GetAvailableAuthProvidersUseCase
import android.example.shop.domain.interactor.GetUserUseCase
import android.example.shop.domain.interactor.IsUserSignedUseCase
import android.example.shop.domain.interactor.UserSignOutUseCase
import android.example.shop.presenter.view.UserView
import android.view.MenuItem
import javax.inject.Inject

class UserPresenter @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val isUserSignedUseCase: IsUserSignedUseCase,
    private val getAvailableAuthProvidersUseCase: GetAvailableAuthProvidersUseCase,
    private val userSignOutUseCase: UserSignOutUseCase
) : BasePresenter<UserView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateUserData()
    }

    fun isSigned() = isUserSignedUseCase()

    fun makeSignIn() {
        val providers = getAvailableAuthProvidersUseCase()

        viewState.showSignInUI(providers)

        updateUserData()
    }

    fun makeSignOut() {
        userSignOutUseCase()

        updateUserData()
    }

    fun signAction(item: MenuItem): Boolean {

    }

    /**
     * Presenter methods
     */
    private fun updateUserData() {
        showUserImage()
        showUserName()
    }

    private fun showUserImage() {
        if (isUserSignedUseCase()) {
            val user = getUserUseCase()

            val userImage = user.photoUrl
            viewState.showUserImage(userImage.toString())
        }
    }

    private fun showUserName() {
        if (isUserSignedUseCase()) {
            val user = getUserUseCase()

            val displayName = user.displayName
            viewState.showUserName(displayName!!)
        }
    }
}