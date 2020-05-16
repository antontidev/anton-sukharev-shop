package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.RemoteProduct
import android.example.shop.presenter.CheckoutPresenter
import android.example.shop.presenter.view.CheckoutView
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.adapters.HorizontalProductsAdapter
import android.example.shop.utils.formatPrice
import android.example.shop.utils.setListener
import android.example.shop.utils.showError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_checkout.*
import javax.inject.Inject

class CheckoutFragment : BaseFragment(),
    CheckoutView {

    @Inject
    lateinit var presenter: CheckoutPresenter

    private val cartAdapter = HorizontalProductsAdapter(
        onClickDescriptionListener = RvItemClickListener {
            presenter.showProductDetail(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()

        presenter.attachView(this)
        catalogCheckoutBtn.setOnClickListener {
            presenter.createOrder()
        }

        radioButtonCash.setOnClickListener {
            presenter.setCashPaymentType()
        }

        radioButtonCard.setOnClickListener {
            presenter.setCardPaymentType()
        }

        cartProducts.adapter = cartAdapter
        cartProducts.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setListeners() {
        checkoutLastName.setListener {
            presenter.checkLastName(it)
        }
        checkoutFirstName.setListener {
            presenter.checkFirstName(it)
        }
        checkoutPhone.setListener {
            presenter.checkPhone(it)
        }
    }

    override fun showErrorFirstName(visible: Boolean) {
        checkoutFirstName.showError(visible)
    }

    override fun showPriceWithDiscount(priceText: Double) {
        price.formatPrice(priceText)
    }

    override fun showDiscount(price: Double) {
        discount.formatPrice(price)
    }

    override fun showPrice(price: Double) {
        priceWithoutDiscount.formatPrice(price)
    }

    override fun showErrorLastName(visible: Boolean) {
        checkoutLastName.showError(visible)
    }

    override fun showErrorPhone(visible: Boolean) {
        checkoutPhone.showError(visible)
    }

    override fun showOrderInfo() {
        Toast.makeText(
            activity, "Уважаемый ${checkoutLastName.text} " +
                    "${checkoutFirstName.text}" +
                    "ваш заказ оформлен!", Toast.LENGTH_LONG
        ).show()
    }

    override fun navigateToDescription(product: RemoteProduct) {
        val action = CheckoutFragmentDirections.actionGlobalDetailFragment(product)

        findNavController().navigate(action)
    }

    override fun setCartProducts(list: List<RemoteProduct>) {
        cartAdapter.setData(list)
    }
}

