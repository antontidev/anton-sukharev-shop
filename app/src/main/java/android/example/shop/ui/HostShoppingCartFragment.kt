package android.example.shop.ui

import android.example.shop.databinding.HostShoppingCartFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.ui.BaseFragment

class HostShoppingCartFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = HostShoppingCartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}