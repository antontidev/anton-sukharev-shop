package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.presenter.DetailPresenter
import android.example.shop.presenter.DescriptionView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_detail.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class DetailFragment : BaseFragment(), DescriptionView {

    @Inject
    lateinit var detailPresenter: DetailPresenter
     private val descriptionPresenter by moxyPresenter {
         detailPresenter
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.fragment_detail, container, false)


        descriptionPresenter.attachView(this)

//        description.text = args.item.fullDescription
//        image.setBackgroundResource(
//            args.item.id
//        )
//        price.formatPrice(args.item)
//        goodName.text = args.item.name

        backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        return view
    }
}