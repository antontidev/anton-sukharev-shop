package android.example.shop.ui

import android.content.Intent
import android.example.shop.databinding.CatalogFragmentBinding
import android.example.shop.domain.VisitedProductDaoImpl.Companion.PRODUCT_TAG
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.example.shop.presenter.CategoryPresenter
import android.example.shop.presenter.CategoryView
import android.example.shop.presenter.VisitedRecentlyPresenter
import android.example.shop.presenter.VisitedRecentlyView
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.adapters.CategoryAdapter
import android.example.shop.utils.adapters.VisitedRecentlyAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseFragment
import moxy.ktx.moxyPresenter

class CatalogFragment : BaseFragment(), CategoryView, VisitedRecentlyView {
    private val categoryPresenter = CategoryPresenter()
    private val recentlyVisitedPresenter by moxyPresenter {
        VisitedRecentlyPresenter(activity?.getSharedPreferences(PRODUCT_TAG, 0)!!)
    }

    private val adapter =
        CategoryAdapter { category ->
            //categoryPresenter.removeItem(category)
            getCategoryProducts(category)
        }

    private val adapterViewedRecently =
        VisitedRecentlyAdapter(
            onClickDescriptionListener = RvItemClickListener {
                showDetailProductInformation(it)
            }
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: CatalogFragmentBinding =
            CatalogFragmentBinding.inflate(inflater, container, false)

        binding.apply {
            backButton.setOnClickListener {
                activity?.onBackPressed()
            }

            val gridLayoutManager = GridLayoutManager(activity, 3)
            catalogRv.layoutManager = gridLayoutManager
            catalogRv.adapter = adapter

            recentlyVisitedRv.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            recentlyVisitedRv.adapter = adapterViewedRecently
        }

        categoryPresenter.attachView(this)
        categoryPresenter.setData()
        recentlyVisitedPresenter.attachView(this)

        return binding.root
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SAVE_INT_STATE, 42)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_AUTH) {
            val userAuth: Boolean? = data?.extras?.getBoolean(IS_USER_AUTH)
            Log.d(tag, userAuth.toString())
        }
    }

    companion object {
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH: Int = 10
        const val SAVE_INT_STATE = "SAVE_INT_STATE"
    }

    private fun showDetailProductInformation(item: TestShoppingCartItemModel) {
//        val action = CatalogFragmentDirections.actionCatalogFragmentToDescriptionFragment(item)
//
//        this.findNavController().navigate(action)
    }

    private fun getCategoryProducts(category: String) {
        val action = CatalogFragmentDirections.actionCatalogFragmentToProductsFragment(category)

        findNavController().navigate(action)
    }

    override fun setCategories(list: List<String>) {
        adapter.setData(list)
    }

    override fun addRecentlyVisited() {
        adapterViewedRecently.notifyItemInserted(0)
    }

    override fun setRecentlyViewed(list: List<TestShoppingCartItemModel>) {
        adapterViewedRecently.setData(list)
        adapterViewedRecently.notifyDataSetChanged()
    }


    override fun removeItem(position: Int) {
        adapter.notifyItemRemoved(position)
    }
}
