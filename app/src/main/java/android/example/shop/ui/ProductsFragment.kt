package android.example.shop.ui

import android.example.shop.databinding.ProductsFragmentBinding
import android.example.shop.domain.MainApi
import android.example.shop.domain.MarsProperty
import android.example.shop.domain.RemoteProduct
import android.example.shop.presenter.ProductsPresenter
import android.example.shop.presenter.ProductsView
import android.example.shop.utils.adapters.ProductsAdapter
import android.example.shop.utils.adapters.ProductsMarsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseFragment
import moxy.ktx.moxyPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsFragment : BaseFragment(), ProductsView {
    private val args: ProductsFragmentArgs by navArgs()

    /**
     * Also for testing
     */
    private lateinit var binding: ProductsFragmentBinding

    private val productsPresenter by moxyPresenter {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mars.udacity.com/")
            //.baseUrl("http://207.254.71.167:9191")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MainApi::class.java)
        ProductsPresenter(
            mainApi = service
        )
    }

    private val productsAdapter = ProductsAdapter()
    private val productsMarsAdapter = ProductsMarsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ProductsFragmentBinding.inflate(inflater, container, false)

        /**
         * It'll comeback
         */
//        binding.productsRv.layoutManager = LinearLayoutManager(activity)
//        binding.productsRv.adapter = productsAdapter
        val lManager = GridLayoutManager(activity, 3)
        binding.productsRv.layoutManager = lManager
        binding.productsRv.adapter = productsMarsAdapter
        productsPresenter.attachView(this)

        return binding.root
    }

    override fun setCourseProducts() {
        productsPresenter.data.observe(viewLifecycleOwner, Observer { courseProducts ->
            productsAdapter.setData(courseProducts)
        })
    }

    override fun setMarsProducts() {
        productsPresenter.dataMarsProperty.observe(viewLifecycleOwner, Observer { marsProperties ->
            productsMarsAdapter.setData(marsProperties)
        })
    }
}