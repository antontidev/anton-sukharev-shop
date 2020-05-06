package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.domain.ViewedProductDao
import android.example.shop.domain.interactor.*
import android.example.shop.presenter.MenuPresenter
import android.example.shop.presenter.MenuView
import android.example.shop.utils.CartChangedEvent
import android.example.shop.utils.FavoriteChangedEvent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.ui.BaseActivity
import com.google.android.material.badge.BadgeDrawable
import kotlinx.android.synthetic.main.activity_menu.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject


class MenuActivity : BaseActivity(), MenuView {
    @Inject
    lateinit var viewedProductDao: ViewedProductDao

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

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    @Subscribe
    fun onCartCapacityChanged(event: CartChangedEvent) = addBadge(event.count, R.id.cartFragment)

    @Subscribe
    fun onFavoriteCapacityChanged(event: FavoriteChangedEvent) = addBadge(event.count, R.id.favoriteFragment)

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
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
