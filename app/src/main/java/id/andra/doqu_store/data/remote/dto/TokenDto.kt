package id.andra.doqu_store.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import id.andra.doqu_store.domain.model.Token

@JsonClass(generateAdapter = true)
data class TokenDto(
    @Json(name = "access_token")
    val accessToken: String?,
    @Json(name = "expires_in")
    val expiresIn: Int?,
    @Json(name = "refresh_token")
    val refreshToken: String?,
    @Json(name = "token_type")
    val tokenType: String?
) {
    fun toToken(): Token {
        return Token(
            access = accessToken.orEmpty(),
            refresh = refreshToken.orEmpty()
        )
    }
}
