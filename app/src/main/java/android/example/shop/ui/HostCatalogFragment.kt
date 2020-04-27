package android.example.shop.ui

import android.example.shop.databinding.HostCatalogFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.ui.BaseFragment

class HostCatalogFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = HostCatalogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}