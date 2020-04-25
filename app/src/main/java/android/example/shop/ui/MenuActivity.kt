package android.example.shop.ui

import android.content.Intent
import android.example.shop.R
import android.os.Bundle
import android.view.MotionEvent
import com.daimajia.swipe.SwipeLayout
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.menu_layout.*
import moxy.MvpAppCompatActivity

class MenuActivity: MvpAppCompatActivity() {
    private val isAuth = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.menu_layout))

//        swipeLayout.dragEdgeMap.clear()
//        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, bottomWrapper)


        makeAuth.setOnClickListener {
            startAuthUI()
        }
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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    companion object{
        const val RC_SIGN_IN = 12
    }
}