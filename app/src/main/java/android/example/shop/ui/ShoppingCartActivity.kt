package android.example.shop.ui

import android.example.shop.R
import android.example.shop.utils.setHeader
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.shopping_cart_layout.*

class ShoppingCartActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_cart_layout)


    }


}