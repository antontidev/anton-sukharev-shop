package android.example.shop.ui

import android.example.shop.databinding.ShoppingCartFragmentBinding
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.example.shop.presenter.ShoppingCartPresenter
import android.example.shop.presenter.ShoppingCartView
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.TestDataSetForAddingProducts
import android.example.shop.utils.adapters.ShoppingCartAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseFragment


class ShoppingCartFragment : BaseFragment(), ShoppingCartView {
    private val shoppingCartPresenter = ShoppingCartPresenter()
    private val adapter =
        ShoppingCartAdapter(
            deleteClickListener = RvItemClickListener {
                shoppingCartPresenter.removeItem(it)
            },
            detailInfoClickListener = RvItemClickListener {
                showDetailProductInformation(it)
            })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = ShoppingCartFragmentBinding.inflate(
            inflater,
            container,
            false
        )
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