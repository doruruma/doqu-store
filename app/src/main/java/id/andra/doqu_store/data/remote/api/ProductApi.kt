package id.andra.doqu_store.data.remote.api

import id.andra.doqu_store.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Header

interface ProductApi {
    @GET(ROUTE)
    suspend fun getProducts(
        @Header("Authorization") token: String
    ): ProductDto

    companion object {
        const val ROUTE = "products"
    }
}