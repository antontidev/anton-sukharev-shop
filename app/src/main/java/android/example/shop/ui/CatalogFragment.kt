package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.MainApi
import android.example.shop.domain.ViewedProductDao
import android.example.shop.domain.interactor.AddProductToCartUseCase
import android.example.shop.presenter.CategoryPresenter
import android.example.shop.presenter.CategoryView
import android.example.shop.presenter.VisitedRecentlyPresenter
import android.example.shop.presenter.VisitedRecentlyView
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.TestDataSetForAddingProducts
import android.example.shop.utils.adapters.CategoryAdapter
import android.example.shop.utils.adapters.ViewedAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlinx.android.synthetic.main.fragment_catalog.view.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CatalogFragment : BaseFragment(), CategoryView, VisitedRecentlyView {
    @Inject
    lateinit var mainApi: MainApi

    @Inject
    lateinit var viewedProductDao: ViewedProductDao

    @Inject
    lateinit var addProductToCartUseCase: AddProductToCartUseCase

    @Inject
    lateinit var categoryPresenter: CategoryPresenter

    private val recentlyVisitedPresenter by moxyPresenter {
        VisitedRecentlyPresenter()
    }

    private val adapter =
        CategoryAdapter { category ->

        }

    private val adapterViewedRecently =
        ViewedAdapter(
            onClickDescriptionListener = RvItemClickListener {

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

        viewedProductDao.addProduct(TestDataSetForAddingProducts().getNextItem())

        view.catalogRv.layoutManager = GridLayoutManager(activity, 3)
        view.catalogRv.adapter = adapter

        recentlyVisitedRv.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recentlyVisitedRv.adapter = adapterViewedRecently


        categoryPresenter.attachView(this)
        categoryPresenter.setData()
        recentlyVisitedPresenter.attachView(this)


        return view
    }


    companion object {
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH: Int = 10
        const val SAVE_INT_STATE = "SAVE_INT_STATE"
    }

    override fun setCategories(list: List<String>) {
        adapter.setData(list)
    }

    override fun getProducts(category: String) {
      // val intent = Intent
    }
}
