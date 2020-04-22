package android.example.shop.ui

import android.content.Intent
import android.example.shop.R
import android.example.shop.model.TestShoppingCartItemModel
import android.example.shop.presenter.ShoppingCartPresenter
import android.example.shop.presenter.ShoppingCartView
import android.example.shop.utils.ShoppingCartAdapter
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.shopping_cart_layout.*

class ShoppingCartActivity: BaseActivity(), ShoppingCartView {
    private val shoppingCartPresenter = ShoppingCartPresenter()
    private val adapter = ShoppingCartAdapter() { item ->
        callActivity(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_cart_layout)

        shoppingCartRv.layoutManager = LinearLayoutManager(this)
        shoppingCartRv.adapter = adapter

        shoppingCartPresenter.attachView(this)
        shoppingCartPresenter.setData()

        backButton.setOnClickListener {
            finish()
        }
    }

    override fun removeFromShoppingCart(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun setShoppingCart(list: List<TestShoppingCartItemModel>) {
        adapter.setData(list)
    }

    private fun callActivity(shoppingCartItem: TestShoppingCartItemModel) {
        val intent = Intent(this, ProductDescriptionActivity::class.java)
        intent.putExtra(IMAGE, shoppingCartItem.id)
        intent.putExtra(DESCRIPTION, shoppingCartItem.fullDescription)
        intent.putExtra(PRICE, shoppingCartItem.price)
        startActivity(intent)
    }


    companion object {
        const val IMAGE = "IMAGE"
        const val DESCRIPTION = "DESCRIPTION"
        const val PRICE = "PRICE"
    }
}