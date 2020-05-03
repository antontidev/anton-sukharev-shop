package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.ViewedProductDao
import android.example.shop.domain.interactor.GetCartProductsUseCase
import android.example.shop.domain.interactor.GetFavoriteProductsUseCase
import android.example.shop.presenter.MenuView
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.ui.BaseActivity
import com.google.android.material.badge.BadgeDrawable
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject


class MenuActivity : BaseActivity(), MenuView {
    @Inject
    lateinit var getCartProductsUseCase: GetCartProductsUseCase

    @Inject
    lateinit var getFavoriteProductsUseCase: GetFavoriteProductsUseCase

    @Inject
    lateinit var viewedProductDao: ViewedProductDao

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setSupportActionBar(toolBar)


        navController = findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
        /**
         * I have no idea hot to observe value by badge
         */
        addBadge(998, R.id.favoriteFragment)

        addBadge(10, R.id.cartFragment)

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewedProductDao.clear()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun addBadge(count : Int, id: Int) {
        val badge: BadgeDrawable = bottomNavigation.getOrCreateBadge(id)
        badge.number = count

        badge.isVisible = count > 0
    }
}
