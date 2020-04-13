package android.example.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ShoppingCartView {

    private val presenter = ShoppingCartPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val priceProducts = 10000
        val discountInPercent = 13
        val discountPrice = priceProducts - (priceProducts * discountInPercent / 100.0)

        priceWithoutDiscount.text = priceProducts.toString()
        discount.text = (priceProducts - discountPrice).toString()
        price.text = discountPrice.toString()

        checkoutButton.setOnClickListener {
            val firstName =
            Toast.makeText(this, "Уважаемый ${secondNameEdit.text} " +
                                              "${nameEdit.text} ${thirdNameEdit.text}, " +
                                              "ваш заказ оформлен!", Toast.LENGTH_LONG).show()
        }
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
