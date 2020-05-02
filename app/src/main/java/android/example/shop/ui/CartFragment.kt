package android.example.shop.ui

import android.example.shop.R
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment : BaseFragment(), ShoppingCartView {
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
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        shoppingCartPresenter.attachView(this)
        shoppingCartPresenter.setData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoppingCartRv.layoutManager = LinearLayoutManager(activity)
        shoppingCartRv.adapter = adapter

        addElementButton.setOnClickListener {
            val testData = TestDataSetForAddingProducts()
            shoppingCartPresenter.addItem(testData.getNextItem())
        }

        backButton.setOnClickListener {
            activity?.onBackPressed()
        }
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

    override fun showProductDetail(item: TestShoppingCartItemModel) {

    }

    private fun showDetailProductInformation(shoppingCartItem: TestShoppingCartItemModel) {

    }
}