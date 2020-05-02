package android.example.shop.presenter

import android.content.SharedPreferences
import android.example.shop.domain.model.TestShoppingCartItemModel
import dagger.Module
import moxy.MvpPresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val sharedPreferences: SharedPreferences
): MvpPresenter<DescriptionView>() {

}