package android.example.shop.ui

import android.content.Intent
import android.example.shop.R
import android.example.shop.presenter.CatalogPresenter
import android.example.shop.presenter.CatalogView
import android.example.shop.utils.CategoryAdapter
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.catalog_layout.*

class CatalogActivity: BaseActivity(), CatalogView {
    private val catalogPresenter = CatalogPresenter()
    private val adapter = CategoryAdapter() {category ->
        catalogPresenter.removeItem(category)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)


        backButton.setOnClickListener {
            finish()
        }

        catalogRv.layoutManager = LinearLayoutManager(this)
        catalogRv.adapter = adapter

        catalogPresenter.attachView(this)
        catalogPresenter.setData()
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

    override fun setCategories(list: List<String>) {
        adapter.setData(list)
    }

    override fun removeItem(position: Int) {
        adapter.notifyItemRemoved(position)
    }
}