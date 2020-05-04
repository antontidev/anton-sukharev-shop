package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.ViewedProductDao
import android.example.shop.domain.interactor.GetErrorUseCase
import android.example.shop.domain.model.ErrorModel
import android.example.shop.presenter.ProductsPresenter
import android.example.shop.presenter.ProductsView
import android.example.shop.utils.adapters.ProductsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_products.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ProductsFragment : BaseFragment(), ProductsView {
    @Inject
    lateinit var productsPresenter: ProductsPresenter

    private val productsAdapter = ProductsAdapter(
        onProductClick = {
            productsPresenter.showProductDetail(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_products, container, false)

        productsPresenter.attachView(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsRv.layoutManager = LinearLayoutManager(activity)
        productsRv.adapter = productsAdapter
    }

    override fun navigateToProductDetail(item: RemoteProduct) {
        val action = ProductsFragmentDirections.actionProductsFragmentToDetailFragment(item)

        findNavController().navigate(action)
    }

    override fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun setProducts(list: List<RemoteProduct>) {
        productsAdapter.setData(list)
    }
}