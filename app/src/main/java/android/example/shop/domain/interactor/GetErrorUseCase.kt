package android.example.shop.domain.interactor

import android.example.shop.domain.ErrorHandler
import android.example.shop.domain.model.ErrorModel
import javax.inject.Inject

class GetErrorUseCase @Inject constructor(
    private val errorHandler: ErrorHandler
){
    operator fun invoke() = errorHandler.getError()

    fun errorsCount(): Int = errorHandler.getErrorsCount()
}