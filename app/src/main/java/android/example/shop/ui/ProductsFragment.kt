package android.example.shop.ui

import android.example.shop.databinding.ProductsFragmentBinding
import android.example.shop.domain.MainApi
import android.example.shop.presenter.ProductsPresenter
import android.example.shop.utils.adapters.ProductsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.myapplication.ui.BaseFragment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsFragment : BaseFragment() {
    private val args: ProductsFragmentArgs by navArgs()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://207.254.71.167:9191/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productsAdapter = ProductsAdapter()
    private val productsPresenter = ProductsPresenter(retrofit.create(MainApi::class.java))
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = ProductsFragmentBinding.inflate(inflater, container, false)

        binding.productsRv.adapter = productsAdapter

        productsAdapter.setData(productsPresenter.data)
//
//        binding.apply {
//            productsRv.adapter
//        }
        return binding.root
    }
}