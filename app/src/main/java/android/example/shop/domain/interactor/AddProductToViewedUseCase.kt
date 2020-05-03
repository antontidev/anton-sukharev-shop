package android.example.shop.domain.interactor

import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.ViewedProductDao
import javax.inject.Inject

class AddProductToViewedUseCase @Inject constructor(
    private val viewedProductDao: ViewedProductDao
) {
    operator fun invoke(product: RemoteProduct){
        viewedProductDao.addProduct(product)
    }
}