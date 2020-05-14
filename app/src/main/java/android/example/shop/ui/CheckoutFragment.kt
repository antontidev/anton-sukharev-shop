package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.RemoteProduct
import android.example.shop.presenter.CheckoutPresenter
import android.example.shop.presenter.view.CheckoutView
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.adapters.ViewedAdapter
import android.example.shop.utils.showError
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_checkout.*
import javax.inject.Inject

class CheckoutFragment : BaseFragment(),
    CheckoutView {

    @Inject
    lateinit var presenter: CheckoutPresenter

    private val viewedAdapter = ViewedAdapter(
        onClickDescriptionListener = RvItemClickListener {
            presenter.showProductDetail(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)

        presenter.attachView(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        catalogCheckoutBtn.setOnClickListener {
            val firstName =
                Toast.makeText(
                    activity, "Уважаемый ${checkoutLastName.text} " +
                            "${checkoutFirstName.text} ${checkoutMiddleName.text}, " +
                            "ваш заказ оформлен!", Toast.LENGTH_LONG
                ).show()
        }

        cartProducts.adapter = viewedAdapter
        cartProducts.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        getUserInfo()
    }

    private fun getUserInfo() {
        FirebaseAuth.getInstance().currentUser?.let {
            checkoutFirstName.setText(it.displayName)
        }
    }

    private fun setListeners() {
        checkoutLastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkLastName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutFirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkFirstName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutMiddleName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkMiddleName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPhone(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun showErrorFirstName(visible: Boolean) {
        checkoutFirstName.showError(visible)
    }

    override fun showErrorLastName(visible: Boolean) {
        checkoutLastName.showError(visible)
    }

    override fun showErrorMiddleName(visible: Boolean) {
        checkoutMiddleName.showError(visible)
    }

    override fun showErrorPhone(visible: Boolean) {
        checkoutPhone.showError(visible)
    }

    override fun navigateToDescription(product: RemoteProduct) {
        val action = CheckoutFragmentDirections.actionGlobalDetailFragment(product)

        findNavController().navigate(action)
    }

    override fun setCartProducts(list: List<RemoteProduct>) {
        viewedAdapter.setData(list)
    }
}

