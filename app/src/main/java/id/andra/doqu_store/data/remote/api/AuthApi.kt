package id.andra.doqu_store.data.remote.api

import id.andra.doqu_store.data.remote.dto.TokenDto
import id.andra.doqu_store.data.remote.request.LoginRequest
import id.andra.doqu_store.data.remote.request.RefreshTokenRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST(ROUTE)
    suspend fun login(
        @Body body: LoginRequest
    ): TokenDto

    @POST(ROUTE)
    suspend fun refreshToken(
        @Body requestParam: RefreshTokenRequest
    ): TokenDto

    companion object {
        const val ROUTE = "oauth/token"
    }
}