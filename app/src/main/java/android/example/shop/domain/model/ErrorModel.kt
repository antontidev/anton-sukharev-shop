package android.example.shop.domain.model

class ErrorModel(
    error: Throwable
) {
    private var message: String = error.message!!

    fun getMessage() = message
}