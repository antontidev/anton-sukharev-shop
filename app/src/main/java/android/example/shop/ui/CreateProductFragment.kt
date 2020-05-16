package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.presenter.CreateProductPresenter
import android.example.shop.presenter.view.CreateProductView
import android.example.shop.utils.setListener
import android.example.shop.utils.showError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_create_product.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that ma
class CreateProductFragment : BaseFragment(), CreateProductView {

    @Inject
    lateinit var presenter: CreateProductPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        setListeners()
        addProduct.setOnClickListener {
            presenter.createProduct()
        }
    }

    private fun setListeners() {
        productName.setListener {
            presenter.checkName(it)
        }
        productAuthor.setListener {
            presenter.checkAuthor(it)
        }
        productPrice.setListener {
            presenter.checkPrice(it)
        }
        productDescription.setListener {
            presenter.checkDescription(it)
        }
        productImageUrl.setListener {
            presenter.checkImageUrl(it)
        }
    }

    override fun showResult() {
        Toast.makeText(
            activity, "${productName.text} успешно добавлен", Toast.LENGTH_LONG
        ).show()
    }

    override fun showErrorName(visible: Boolean) {
        productName.showError(visible)
    }

    override fun showErrorUrl(visible: Boolean) {
        productImageUrl.showError(visible)
    }

    override fun showErrorPrice(visible: Boolean) {
        productPrice.showError(visible)
    }

    override fun showErrorDescription(visible: Boolean) {
        productDescription.showError(visible)
    }

    override fun showErrorAuthor(visible: Boolean) {
        productAuthor.showError(visible)
    }


}
