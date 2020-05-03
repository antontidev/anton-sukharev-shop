package android.example.shop.domain.model

sealed class ErrorModel {
    object network: ErrorModel()
}