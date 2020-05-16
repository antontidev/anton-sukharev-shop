package android.example.shop.presenter

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.interactor.AddProductUseCase
import android.example.shop.domain.model.CreateProductModel
import android.example.shop.presenter.view.CreateProductView
import javax.inject.Inject
import kotlin.random.Random

class CreateProductPresenter @Inject constructor(
    private val addProductUseCase: AddProductUseCase
) : BasePresenter<CreateProductView>() {
    private fun checkSymbols(text: String) = text.length < 3

    private val createProductModel =
        CreateProductModel()

    fun createProduct() {
        launch {
            val product = RemoteProduct(
                id = Random.nextLong().toString(),
                name = createProductModel.name,
                price = createProductModel.price,
                discountPercent = 0,
                description = createProductModel.description,
                imageUrl = createProductModel.imageUrl,
                attributes = listOf()
            )
            addProductUseCase(createProductModel.author, product)
        }
        viewState.showResult()
    }

    fun checkDescription(it: String) {
        if (!checkSymbols(it)) createProductModel.description = it
        viewState.showErrorDescription(checkSymbols(it))
    }

    fun checkPrice(it: String) {
        if (!checkSymbols(it)) createProductModel.price = it.toDouble()
        viewState.showErrorPrice(checkSymbols(it))
    }

    fun checkAuthor(it: String) {
        if (!checkSymbols(it)) createProductModel.author = it
        viewState.showErrorAuthor(checkSymbols(it))
    }

    fun checkName(it: String) {
        if (!checkSymbols(it)) createProductModel.name = it
        viewState.showErrorName(checkSymbols(it))
    }

    fun checkImageUrl(it: String) {
        if (!checkSymbols(it)) createProductModel.imageUrl = it
        viewState.showErrorUrl(checkSymbols(it))
    }
}