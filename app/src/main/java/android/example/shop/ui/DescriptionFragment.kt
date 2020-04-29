package android.example.shop.ui

import android.example.shop.databinding.DescriptionFragmentBinding
import android.example.shop.domain.VisitedProductDaoImpl.Companion.PRODUCT_TAG
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.example.shop.presenter.DescriptionPresenter
import android.example.shop.presenter.DescriptionView
import android.example.shop.utils.formatPrice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.myapplication.ui.BaseFragment
import moxy.ktx.moxyPresenter

class DescriptionFragment : BaseFragment(), DescriptionView {
    private val args: DescriptionFragmentArgs by navArgs()

    private val descriptionPresenter by moxyPresenter {
        DescriptionPresenter(activity?.getSharedPreferences(PRODUCT_TAG, 0)!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: DescriptionFragmentBinding = DescriptionFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        descriptionPresenter.addVisited(args.item)
        descriptionPresenter.attachView(this)

        binding.apply {
            description.text = args.item.fullDescription
            image.setBackgroundResource(
                args.item.id
            )
            price.formatPrice(args.item)
            goodName.text = args.item.name

            backButton.setOnClickListener {
                //activity?.onBackPressed()
            }
        }

        return binding.root
    }
}