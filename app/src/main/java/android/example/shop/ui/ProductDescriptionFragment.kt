package android.example.shop.ui

import android.example.shop.databinding.DescriptionFragmentBinding
import android.example.shop.utils.formatPrice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.myapplication.ui.BaseFragment

class ProductDescriptionFragment: BaseFragment() {
    private val args: ProductDescriptionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: DescriptionFragmentBinding = DescriptionFragmentBinding.inflate(inflater,
            container,
            false)

        binding.apply {
            description.text = args.shoppingCartItem.fullDescription
            image.setBackgroundResource(
                args.shoppingCartItem.id
            )
            price.formatPrice(args.shoppingCartItem)
            goodName.text = args.shoppingCartItem.name

            backButton.setOnClickListener {
                activity?.onBackPressed()
            }
        }

        return binding.root
    }
}