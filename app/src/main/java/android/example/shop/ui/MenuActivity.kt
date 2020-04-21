package android.example.shop.ui

import android.example.shop.R
import android.os.Bundle
import com.example.myapplication.ui.BaseActivity

class MenuActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.menu_layout))
    }
}