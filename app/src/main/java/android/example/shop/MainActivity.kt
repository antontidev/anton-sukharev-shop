package android.example.shop

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ShoppingCartView {

    private val presenter = ShoppingCartPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)

        val priceProducts = 10000
        val discountInPercent = 13
        val discountPrice = priceProducts - (priceProducts * discountInPercent / 100.0)

        priceWithoutDiscount.text = priceProducts.toString()
        discount.text = (priceProducts - discountPrice).toString()
        price.text = discountPrice.toString()

        checkoutButton.setOnClickListener {
            val firstName =
            Toast.makeText(this, "Уважаемый ${checkoutName.text} " +
                                              "${nameEdit.text} ${thirdNameEdit.text}, " +
                                              "ваш заказ оформлен!", Toast.LENGTH_LONG).show()
        }

        setListeners()

        /**
         * This call could be removed someday
         */
        presenter.printShoppingCart()
    }

    private fun setListeners() {
        checkoutName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkSecondName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        nameEdit.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkFirstName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        thirdNameEdit.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkMiddleName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun EditText.showError(visible: Boolean) {
        val drawable = if (visible) R.drawable.ic_error
        else 0

        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }

    override fun showErrorFirstName(visible: Boolean) {
        nameEdit.showError(visible)
    }

    override fun showErrorSecondName(visible: Boolean) {
        checkoutName.showError(visible)
    }

    override fun showErrorMiddlename(visible: Boolean) {
        thirdNameEdit.showError(visible)
    }

    /**
     * This methods could be removed someday
     */
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

