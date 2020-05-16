package android.example.shop.ui

import android.content.Context
import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.RemoteCategory
import android.example.shop.domain.RemoteProduct
import android.example.shop.presenter.CatalogPresenter
import android.example.shop.presenter.view.CatalogView
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.adapters.CategoryAdapter
import android.example.shop.utils.adapters.HorizontalProductsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_catalog.*
import javax.inject.Inject


class CatalogFragment : BaseFragment(),
    CatalogView {
    @Inject
    lateinit var catalogPresenter: CatalogPresenter

    private lateinit var adapter: CategoryAdapter

    private val adapterViewedRecently =
        HorizontalProductsAdapter(
            onClickDescriptionListener = RvItemClickListener {
                catalogPresenter.showProductDetail(it)
            }
        )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = CategoryAdapter(
            context,
            onCategoryClick = {
                catalogPresenter.showCategoryProducts(it)
            },
            onProductClick = {
                catalogPresenter.showProductDetail(it)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        catalogPresenter.attachView(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catalogRv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        catalogRv.adapter = adapter

        recentlyVisitedRv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recentlyVisitedRv.adapter = adapterViewedRecently

        swipeContainer.setOnRefreshListener { // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            catalogPresenter.setData()
            swipeContainer.isRefreshing = false
        }
        fab.setOnClickListener {
            catalogPresenter.showCreateProduct()
        }
    }

    override fun setRecentlyVisitedData(list: List<RemoteProduct>) {
        adapterViewedRecently.setData(list)
    }

    override fun setCategories(list: List<RemoteCategory>) {
        adapter.setData(list)
    }

    override fun navigateToCategory(category: String) {
        val action = CatalogFragmentDirections.actionCatalogFragmentToProductsFragment(category)

        findNavController().navigate(action)
    }

    override fun navigateToCreateProduct() {
        val action = CatalogFragmentDirections.actionCatalogFragmentToCreateProductFragment()

        findNavController().navigate(action)
    }

    override fun navigateToOrder() {
        val action = CatalogFragmentDirections.actionCatalogFragmentToOrderFragment()

        findNavController().navigate(action)
    }


    override fun navigateToDetail(product: RemoteProduct) {
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