package id.andra.doqu_store.data.remote.request

import com.squareup.moshi.Json
import id.andra.doqu_store.BuildConfig

data class RefreshTokenRequest(
    @Json(name = "grant_type")
    val grantType: String = "refresh_token",
    @Json(name = "refresh_token")
    val refreshToken: String = "",
    @Json(name = "client_id")
    val clientId: String = BuildConfig.CLIENT_ID,
    @Json(name = "client_secret")
    val clientSecret: String = BuildConfig.CLIENT_SECRET,
    val scope: String = "*"
)