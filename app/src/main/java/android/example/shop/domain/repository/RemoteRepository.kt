package android.example.shop.domain.repository

import android.example.shop.domain.RemoteProduct
import retrofit2.Response

interface RemoteRepository {
    suspend fun getAllProducts(): List<RemoteProduct>
}