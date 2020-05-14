package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.RemoteProduct
import android.example.shop.presenter.CartPresenter
import android.example.shop.presenter.view.CartView
import android.example.shop.utils.CartChangedEvent
import android.example.shop.utils.adapters.ShoppingCartAdapter
import android.example.shop.utils.formatPrice
import android.example.shop.utils.viewVisibilityToBoolean
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cart.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject


class CartFragment : BaseFragment(),
    CartView {
    @Inject
    lateinit var shoppingCartPresenter: CartPresenter
    private val adapter =
        ShoppingCartAdapter(
            deleteClickListener = {
                shoppingCartPresenter.removeItem(it)
            },
            detailInfoClickListener = {
                shoppingCartPresenter.showProductDetail(it)
            })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoppingCartRv.layoutManager = LinearLayoutManager(activity)
        shoppingCartRv.adapter = adapter

        shoppingCartPresenter.attachView(this)
        shoppingCartPresenter.showCartTotalPrice()

        checkoutButton.setOnClickListener {
            shoppingCartPresenter.navigateToCheckout()
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe
    fun onCartCapacityChanged(event: CartChangedEvent) {
        shoppingCartPresenter.apply {
            showCart()
            showCartTotalPrice()
        }
    }

    override fun showCart(empty: Boolean) {
        contentCart.visibility = viewVisibilityToBoolean(empty)
        emptyCartText.visibility = viewVisibilityToBoolean(!empty)
    }

    override fun removeCartProduct(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun setCartProducts(list: List<RemoteProduct>) {
        adapter.setData(list)
    }

    override fun addCartProduct(position: Int) {
        adapter.notifyItemInserted(position)
    }

    override fun navigateToProductDetail(product: RemoteProduct) {
        val action = CartFragmentDirections.actionCartFragmentToDetailFragment(product)

        findNavController().navigate(action)
    }

    override fun showTotalPrice(price: Double) {
        totalPrice.formatPrice(price)
    }

    override fun navigateToCheckout() {
        val action = CartFragmentDirections.actionCartFragmentToCheckoutFragment()

        findNavController().navigate(action)
    }
}