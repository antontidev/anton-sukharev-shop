package android.example.shop.ui

import android.content.Intent
import android.example.shop.R
import android.os.Bundle
import com.example.myapplication.ui.BaseActivity
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.menu_layout.*

class MenuActivity: BaseActivity() {
    private val isAuth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.menu_layout))

        makeAuth.setOnClickListener {
            val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
                RC_SIGN_IN)
        }

        checkoutProducts.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }


        toCatalog.setOnClickListener {
            val intent = Intent(this, CatalogActivity::class.java)
            startActivity(intent)
        }

        toShoppingCart.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        }
    }


    companion object{
        const val RC_SIGN_IN = 12
    }
}