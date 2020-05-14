package android.example.shop.ui

import android.view.MenuItem

interface StatefulMenuItem {
    fun getIcon(state: Boolean): Int

    fun setupMenuItem(item: MenuItem): Boolean
}