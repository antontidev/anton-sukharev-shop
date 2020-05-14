package android.example.shop.presenter.view

import com.firebase.ui.auth.AuthUI
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun showUserImage(url: String)

    fun showUserName(name: String)

    fun showSignInUI(providers: List<AuthUI.IdpConfig>)

    fun showSignButton(): Boolean
}