package android.example.shop.presenter

import moxy.MvpPresenter

class CatalogPresenter: MvpPresenter<CatalogView>() {
    val list = mutableListOf("Телевизоры", "Телефоны", "Планшеты")

    fun setData() {
        viewState.setCategories(list)
    }

    fun removeItem(string: String) {
        val position = list.indexOf(string)
        list.remove(string)
        viewState.removeItem(position)
    }

}