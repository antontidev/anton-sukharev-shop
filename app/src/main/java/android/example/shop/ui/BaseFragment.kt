package com.example.myapplication.ui

import android.example.shop.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import moxy.MvpAppCompatFragment


class BaseFragment<A> : MvpAppCompatFragment() {
    private val rootDestinations = setOf(R.id.catalogFragment, R.id.shoppingCartFragment)

    private val defaultInt = -1
    private var layoutRes: Int = -1
    private var toolbarId: Int = -1
    private var navHostId: Int = -1

    private val appBarConfig = AppBarConfiguration(rootDestinations)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // extract arguments from bundle
        arguments?.let {
            layoutRes = it.getInt(KEY_LAYOUT)
            toolbarId = it.getInt(KEY_TOOLBAR)
            navHostId = it.getInt(KEY_NAV_HOST)

        } ?: return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return if (layoutRes == defaultInt) null
        else inflater.inflate(layoutRes, container, false)
    }

    override fun onStart() {
        super.onStart()
        // return early if no arguments were parsed
        if (toolbarId == defaultInt || navHostId == defaultInt) return
        // find navController using navHostFragment
        val navController = requireActivity().findNavController(navHostId)
        // setup navigation with root destinations and toolbar
      //  NavigationUI.setupWithNavController(toolbar, navController, appBarConfig)

    }

    override fun onResume() {
        super.onResume()
        // The activity has become visible (it is now "resumed").
        Log.d(tag, "onResume")

    }

    override fun onPause() {
        super.onPause()
        // Another activity is taking focus (this activity is about to be "paused").
        Log.d(tag, "onPause")

    }

    override fun onStop() {
        super.onStop()
        // The activity is no longer visible (it is now "stopped")
        Log.d(tag, "onStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        // The activity is about to be destroyed.
        Log.d(tag, "onDestroy")
    }
    companion object {
        private const val KEY_LAYOUT = "layout_key"
        private const val KEY_NAV_HOST = "nav_host_key"
        private const val KEY_TOOLBAR = "toolbar_key"

        fun newInstance(layoutRes: Int, toolbarId: Int, navHostId: Int) = BaseFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_LAYOUT, layoutRes)
                putInt(KEY_TOOLBAR, toolbarId)
                putInt(KEY_NAV_HOST, navHostId)
            }
        }
    }
}