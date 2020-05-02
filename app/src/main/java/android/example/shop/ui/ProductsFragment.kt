package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.ViewedProductDao
import android.example.shop.presenter.ProductsPresenter
import android.example.shop.presenter.ProductsView
import android.example.shop.utils.adapters.ProductsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_products.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ProductsFragment : BaseFragment(), ProductsView {
    @Inject
    lateinit var mainApi: MainApi

    @Inject
    lateinit var visitedProductDao: ViewedProductDao

    private val productsPresenter by moxyPresenter {
        ProductsPresenter(
            mainApi
        )
    }

    private val productsAdapter = ProductsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_products, container, false)
        productsRv.layoutManager = LinearLayoutManager(activity)
        productsRv.adapter = productsAdapter

        productsPresenter.attachView(this)

        return view
    }

    override fun setProducts(list: List<RemoteProduct>) {
        productsAdapter.setData(list)
    }
}