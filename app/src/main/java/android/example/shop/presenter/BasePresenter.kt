package android.example.shop.presenter

import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<KView: MvpView>() : MvpPresenter<KView>() {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    fun launch(action: suspend () -> Unit) {
        scope.launch {
            action()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}