package android.example.shop.ui

import android.example.shop.databinding.DescriptionFragmentBinding
import android.example.shop.utils.formatPrice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.myapplication.ui.BaseFragment

class DescriptionFragment : BaseFragment() {
    private val args: DescriptionFragmentArgs by navArgs()

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