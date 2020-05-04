package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.ViewedProductDao
import android.example.shop.domain.interactor.*
import android.example.shop.domain.model.ErrorModel
import android.example.shop.presenter.MenuPresenter
import android.example.shop.presenter.MenuView
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.ui.BaseActivity
import com.google.android.material.badge.BadgeDrawable
import kotlinx.android.synthetic.main.activity_menu.*
import javax.inject.Inject


class MenuActivity : BaseActivity(), MenuView {
    @Inject
    lateinit var viewedProductDao: ViewedProductDao

    @Inject
    lateinit var getFavoriteProductsCountUseCase: GetFavoriteProductsCountUseCase

    @Inject
    lateinit var getCartProductsCountUseCase: GetCartProductsCountUseCase

    @Inject
    lateinit var menuPresenter: MenuPresenter
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

        menuPresenter.attachView(this)

        getCartProductsCountUseCase().observe(this, Observer {
            addBadge(it, R.id.cartFragment)
        })

        getFavoriteProductsCountUseCase().observe(this, Observer {
            addBadge(it, R.id.favoriteFragment)
        })

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

    override fun addBadge(count : Int, id: Int) {
        val badge: BadgeDrawable = bottomNavigation.getOrCreateBadge(id)
        badge.number = count

        badge.isVisible = count > 0
    }
}
