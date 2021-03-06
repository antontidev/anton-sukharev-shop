package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.presenter.MenuPresenter
import android.example.shop.presenter.view.MenuView
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


class MenuActivity : BaseActivity(),
    MenuView {
    @Inject
    lateinit var menuPresenter: MenuPresenter

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        navController = findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavigation, navController)

        menuPresenter.attachView(this)
    }

    @Subscribe
    fun onCartCapacityChanged(event: CartChangedEvent) = menuPresenter.setCartBadge(event.count)

    @Subscribe
    fun onFavoriteCapacityChanged(event: FavoriteChangedEvent) =
        menuPresenter.setFavoriteBadge(event.count)

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    override fun showFavoriteBadge(count: Int) {
        val badge: BadgeDrawable = bottomNavigation.getOrCreateBadge(R.id.favoriteFragment)
        badge.number = count

        badge.isVisible = count > 0
    }

    override fun showCartBadge(count: Int) {
        val badge: BadgeDrawable = bottomNavigation.getOrCreateBadge(R.id.cartFragment)
        badge.number = count

        badge.isVisible = count > 0
    }
}
