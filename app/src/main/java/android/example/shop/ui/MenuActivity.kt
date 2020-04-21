package android.example.shop.ui

import android.example.shop.R
import android.os.Bundle
import com.example.myapplication.ui.BaseActivity
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.menu_layout.*

class MenuActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.menu_layout))

        makeAuth.setOnClickListener {
            val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())

//            startActivityForResult(
//                AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setAvailableProviders(providers)
//                    .build(),
//                RC_SIGN_IN)
        }
    }


    companion object{
        const val RC_SIGN_IN = 12
    }
}