package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.presenter.DetailPresenter
import android.example.shop.presenter.DescriptionView
import android.example.shop.utils.bindImage
import android.example.shop.utils.formatPrice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_detail.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class DetailFragment : BaseFragment(), DescriptionView {
    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var detailPresenter: DetailPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailPresenter.attachView(this)

        args.apply {
            description.text = product.description

            image.bindImage(product.imageUrl)
            price.formatPrice(product)
            goodName.text = product.name
        }
    }
}