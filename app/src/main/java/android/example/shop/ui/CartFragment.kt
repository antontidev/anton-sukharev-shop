package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.RemoteProduct
import android.example.shop.presenter.CartPresenter
import android.example.shop.presenter.CartView
import android.example.shop.utils.adapters.ShoppingCartAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject


class CartFragment : BaseFragment(), CartView {
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

        shoppingCartPresenter.attachView(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoppingCartRv.layoutManager = LinearLayoutManager(activity)
        shoppingCartRv.adapter = adapter
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
}