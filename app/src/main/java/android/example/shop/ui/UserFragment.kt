package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.presenter.UserPresenter
import android.example.shop.presenter.view.UserView
import android.example.shop.utils.bindImage
import android.os.Bundle
import android.view.*
import com.example.myapplication.ui.BaseActivity
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject

class UserFragment : BaseFragment(),
    UserView {

    lateinit var signButton: MenuItem

    @Inject
    lateinit var userPresenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userPresenter.attachView(this)

        (requireActivity() as BaseActivity).setSupportActionBar(toolBar)
    }

    override fun showSignInUI(providers: List<AuthUI.IdpConfig>) {
        startActivity(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.user_items, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val signed = userPresenter.isSigned()

        val item = menu.findItem(R.id.action_menu_login)

        val icon = getIcon(signed)
        item.setIcon(icon)

        signButton = item
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_menu_login -> {
                userPresenter.signAction(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showUserImage(url: String) {
        userAccountImage.bindImage(url)
    }

    override fun showUserName(name: String) {
        userAccountName.text = name
    }

    private fun getIcon(state: Boolean): Int {
        return when (state) {
            false -> R.drawable.ic_input
            true -> R.drawable.ic_error
        }
    }

//    override fun showSignButton(): Boolean {
////        val icon = getIcon(item.isChecked)
////
////        if (item.isChecked) userPresenter.makeSignIn()
////        else userPresenter.makeSignOut()
////
////        item.setIcon(icon)
//        return true
//    }
}