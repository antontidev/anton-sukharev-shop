package android.example.shop.ui

import android.example.shop.R
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.adapters.ScreenPagerAdapter
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myapplication.ui.BaseActivity
import com.example.myapplication.ui.BaseFragment
import com.firebase.ui.auth.AuthUI
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.menu_layout.*
import java.util.*
import kotlin.reflect.KClass

class MenuActivity : BaseActivity() {
    private val isAuth = false

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.menu_layout))

        val fragmentList = arrayListOf<BaseFragment>(
            UserInfoFragment(),
            HostCatalogFragment(),
            HostShoppingCartFragment()
        )

        viewPager.adapter = ScreenPagerAdapter(this, fragmentList)
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position -> // Styling each tab here
                tab.text = "Tab $position"
            }).attach()
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