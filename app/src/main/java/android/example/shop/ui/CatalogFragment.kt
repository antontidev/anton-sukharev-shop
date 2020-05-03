package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.MainApi
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.ViewedProductDao
import android.example.shop.domain.interactor.AddProductToCartUseCase
import android.example.shop.presenter.CategoryPresenter
import android.example.shop.presenter.CategoryView
import android.example.shop.presenter.VisitedRecentlyPresenter
import android.example.shop.presenter.VisitedRecentlyView
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.adapters.CategoryAdapter
import android.example.shop.utils.adapters.ViewedAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_catalog.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CatalogFragment : BaseFragment(), CategoryView, VisitedRecentlyView {
    @Inject
    lateinit var addProductToCartUseCase: AddProductToCartUseCase

    private val categoryPresenter: CategoryPresenter by moxyPresenter {
        CategoryPresenter()
    }

    @Inject
    lateinit var recentlyVisitedPresenter: VisitedRecentlyPresenter

    private val adapter =
        CategoryAdapter(
            onCategoryClick = {
                categoryPresenter.showCategoryProducts(it)
            }
        )

    private val adapterViewedRecently =
        ViewedAdapter(
            onClickDescriptionListener = RvItemClickListener {
                recentlyVisitedPresenter.showProductDetail(it)
            }
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        categoryPresenter.attachView(this)
        categoryPresenter.setData()
        recentlyVisitedPresenter.attachView(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catalogRv.layoutManager = GridLayoutManager(activity, 3)
        catalogRv.adapter = adapter

        recentlyVisitedRv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recentlyVisitedRv.adapter = adapterViewedRecently
        recentlyVisitedPresenter
    }

    override fun setRecentlyVisitedData(list: List<RemoteProduct>) {
        adapterViewedRecently.setData(list)
    }

    override fun setCategories(list: List<String>) {
        adapter.setData(list)
    }

    override fun navigateToCategory(category: String) {
        val action = CatalogFragmentDirections.actionCatalogFragmentToProductsFragment(category)

        findNavController().navigate(action)
    }

    override fun navigateToProductDetail(product: RemoteProduct) {
        val action = CatalogFragmentDirections.actionCatalogFragmentToDetailFragment(product)

        findNavController().navigate(action)
    }

    companion object {
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH: Int = 10
        const val SAVE_INT_STATE = "SAVE_INT_STATE"
    }

}