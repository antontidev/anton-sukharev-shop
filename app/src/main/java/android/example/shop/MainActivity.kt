package android.example.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity(), ShoppingCartView {

    private val presenter = ShoppingCartPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.printShoppingCart()
    }

    override fun print(price: Double) {
        Log.i("Homework", "$price")
    }

    override fun print(name: String) {
        Log.i("Homework", "$name")
    }

    override fun print(product: Product) {
        Log.i("Homework", "${product.getProductName()}: ${product.calcDiscountPrice()}")
    }

}
