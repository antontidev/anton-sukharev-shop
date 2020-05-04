package android.example.shop.domain

import android.example.shop.domain.model.ErrorModel
import androidx.lifecycle.LiveData

interface ErrorHandler {
    fun getError(): ErrorModel

    fun dispatchError(error: ErrorModel)

    fun getErrorsCount(): Int
}