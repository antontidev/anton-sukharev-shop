package android.example.shop.ui

import android.example.shop.R
import android.example.shop.ui.ShoppingCartActivity.Companion.DESCRIPTION
import android.example.shop.ui.ShoppingCartActivity.Companion.IMAGE
import android.example.shop.ui.ShoppingCartActivity.Companion.NAME
import android.example.shop.ui.ShoppingCartActivity.Companion.PRICE
import android.os.Bundle
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.product_description_layout.*

class ProductDescriptionActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_description_layout)

        description.text = intent.getStringExtra(DESCRIPTION)
        image.setBackgroundResource(intent.getIntExtra(IMAGE, -1))
        price.text = intent.getStringExtra(PRICE)
        goodName.text = intent.getStringExtra(NAME)

        backButton.setOnClickListener {
            finish()
        }
    }

}