package android.example.shop.domain

import android.example.shop.domain.model.ErrorModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class ErrorHandlerImpl: ErrorHandler {
    private var count: Int = 0

    private val errors = Stack<ErrorModel>()

    override fun getError(): ErrorModel {
        val error = errors.pop()
        count = errors.size
        return error
    }

    override fun dispatchError(error: ErrorModel) {
        errors.push(error)
        count = errors.size
    }

    override fun getErrorsCount() = count
}