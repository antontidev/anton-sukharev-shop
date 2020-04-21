package android.example.shop.ui

import android.app.Activity
import android.content.Intent
import android.example.shop.R
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.catalog_layout.*

class CatalogActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)




        catalogCheckoutBtn.setOnClickListener{
            val intent = Intent(this, CheckoutActivity::class.java).apply {
                putExtra(PRODUCT_ID, 1000)
            }
            startActivityForResult(intent, REQUEST_AUTH)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SAVE_INT_STATE, 42)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_AUTH) {
            val userAuth: Boolean? = data?.extras?.getBoolean(IS_USER_AUTH)
            Log.d(tag, userAuth.toString())
        }
    }

    companion object {
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH: Int = 10
        const val SAVE_INT_STATE = "SAVE_INT_STATE"
    }
}