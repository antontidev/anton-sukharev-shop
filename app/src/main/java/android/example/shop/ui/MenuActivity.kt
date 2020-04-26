package android.example.shop.ui

import android.example.shop.R
import android.example.shop.databinding.CatalogFragmentBinding
import android.example.shop.databinding.ShoppingCartFragmentBinding
import android.example.shop.databinding.UserInfoFragmentBinding
import android.example.shop.utils.adapters.ScreenPagerAdapter
import android.os.Bundle
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import com.example.myapplication.ui.BaseActivity
import com.example.myapplication.ui.BaseFragment
import com.firebase.ui.auth.AuthUI
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.menu_layout.*

class MenuActivity : BaseActivity() {
    private val isAuth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.menu_layout))

        val fragmentList = arrayListOf<BaseFragment>(
            UserInfoFragment(),
            CatalogFragment(),
            ShoppingCartFragment()
        )

        viewPager.adapter = ScreenPagerAdapter(this, fragmentList)

        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position -> // Styling each tab here
                tab.text = "Tab $position"
            }).attach()

        /**
         * Will be deleted
         */
//        swipeLayout.dragEdgeMap.clear()
//        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, bottomWrapper)

//        makeAuth.setOnClickListener {
//            startAuthUI()

//        }
    }

    private fun startAuthUI() {
        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    companion object {
        const val RC_SIGN_IN = 12
    }
}