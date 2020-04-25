package android.example.shop.ui

import android.content.Intent
import android.example.shop.R
import android.example.shop.databinding.ShoppingCartFragmentBinding
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.example.shop.presenter.ShoppingCartPresenter
import android.example.shop.presenter.ShoppingCartView
import android.example.shop.utils.ShoppingCartAdapter
import android.example.shop.utils.TestDataSetForAddingProducts
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseActivity
import com.example.myapplication.ui.BaseFragment
import kotlinx.android.synthetic.main.shopping_cart_fragment.*


class ShoppingCartFragment: BaseFragment(), ShoppingCartView {
    private val shoppingCartPresenter = ShoppingCartPresenter()
    private val adapter = ShoppingCartAdapter(
        deleteClickListener = ShoppingCartAdapter.ShoppingItemClickListener {
            shoppingCartPresenter.removeItem(it)
        },
        detailInfoClickListener = ShoppingCartAdapter.ShoppingItemClickListener {
            showDetailProductInformation(it)
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = ShoppingCartFragmentBinding.inflate(inflater,
            container,
            false)
        binding.shoppingCartRv.layoutManager = LinearLayoutManager(activity)
        binding.shoppingCartRv.adapter = adapter

        binding.addElementButton.setOnClickListener {
            val testData = TestDataSetForAddingProducts()
            shoppingCartPresenter.addItem(testData.getNextItem())
        }

        shoppingCartPresenter.attachView(this)
        shoppingCartPresenter.setData()

        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        return binding.root
    }

    override fun removeFromShoppingCart(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun setShoppingCart(list: List<TestShoppingCartItemModel>) {
        adapter.setData(list)
    }

    override fun addShoppingCartItem(position: Int) {
        adapter.notifyItemInserted(position)
    }

    private fun showDetailProductInformation(shoppingCartItem: TestShoppingCartItemModel) {
        val action = ShoppingCartFragmentDirections
            .actionShoppingCartFragmentToProductDescriptionFragment(shoppingCartItem)

        this.findNavController().navigate(action)
    }
}