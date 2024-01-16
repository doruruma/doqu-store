package id.andra.doqu_store.data.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import id.andra.doqu_store.BuildConfig

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val username: String = "",
    val password: String = "",
    @Json(name = "grant_type")
    val grantType: String = "password",
    @Json(name = "client_id")
    val clientId: String = BuildConfig.CLIENT_ID,
    @Json(name = "client_secret")
    val clientSecret: String = BuildConfig.CLIENT_SECRET,
    val scope: String = "*"
)
