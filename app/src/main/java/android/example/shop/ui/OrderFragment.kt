package android.example.shop.ui

import android.content.Intent
import android.example.shop.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class OrderFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(requireActivity(), CameraActivity::class.java)

        startActivityForResult(intent, 12)
    }
}