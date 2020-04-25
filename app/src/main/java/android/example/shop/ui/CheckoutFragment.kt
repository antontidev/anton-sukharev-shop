package android.example.shop.ui

import android.content.Intent
import android.example.shop.presenter.CheckoutView
import android.example.shop.presenter.CkeckoutViewPresenter
import android.example.shop.Product
import android.example.shop.R
import android.example.shop.databinding.CheckoutFragmentBinding
import android.example.shop.ui.CatalogFragment.Companion.IS_USER_AUTH
import android.example.shop.ui.CatalogFragment.Companion.PRODUCT_ID
import android.example.shop.ui.CatalogFragment.Companion.REQUEST_AUTH
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.ui.BaseActivity
import com.example.myapplication.ui.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.checkout_fragment.*

class CheckoutFragment : BaseFragment(),
    CheckoutView {

    private val presenter = CkeckoutViewPresenter()
    private var isAuth = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: CheckoutFragmentBinding = CheckoutFragmentBinding.inflate(inflater,
            container,
            false)

        getUserInfo()

        presenter.attachView(this)

        //val productID = intent.extras?.getInt(PRODUCT_ID, -1)

        //Log.d(tag, productID.toString())

        backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        val priceProducts = 10000
        val discountInPercent = 13
        val discountPrice = priceProducts - (priceProducts * discountInPercent / 100.0)

        priceWithoutDiscount.text = priceProducts.toString()
        discount.text = (priceProducts - discountPrice).toString()
        price.text = discountPrice.toString()

        checkoutPay.setOnClickListener {
            isAuth = true
//            setResult(REQUEST_AUTH, Intent().apply {
//                putExtra(IS_USER_AUTH, isAuth )
//            })
        }

        catalogCheckoutBtn.setOnClickListener {
            val firstName =
                Toast.makeText(activity, "Уважаемый ${checkoutLastName.text} " +
                        "${checkoutFirstName.text} ${checkoutMiddleName.text}, " +
                        "ваш заказ оформлен!", Toast.LENGTH_LONG).show()
        }

        setListeners()

        /**
         * This call could be removed someday
         */
        presenter.printShoppingCart()

        return binding.root
    }


    private fun getUserInfo() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        checkoutFirstName.setText(currentUser?.displayName)
    }

    private fun setListeners() {
        checkoutLastName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkLastName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutFirstName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkFirstName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutMiddleName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkMiddleName(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutPhone.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPhone(s.toString())
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
        checkoutFirstName.showError(visible)
    }

    override fun showErrorLastName(visible: Boolean) {
        checkoutLastName.showError(visible)
    }

    override fun showErrorMiddleName(visible: Boolean) {
        checkoutMiddleName.showError(visible)
    }

    override fun showErrorPhone(visible: Boolean) {
        checkoutPhone.showError(visible)
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

