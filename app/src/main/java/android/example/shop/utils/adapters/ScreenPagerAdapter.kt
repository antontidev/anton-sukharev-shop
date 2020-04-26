package android.example.shop.utils.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.BaseFragment

class ScreenPagerAdapter(
    fa: FragmentActivity,
    private val fragmentList: ArrayList<BaseFragment>
) : FragmentStateAdapter(fa) {
    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int): BaseFragment {
        return fragmentList[position]
    }
}