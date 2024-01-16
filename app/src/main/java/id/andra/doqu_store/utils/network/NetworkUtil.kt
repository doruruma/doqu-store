package id.andra.doqu_store.utils.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.andra.doqu_store.BuildConfig
import id.andra.doqu_store.utils.Var
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> createRetrofit(): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(createOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
        .build()
    return retrofit.create(T::class.java)
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addInterceptor(createHttpLoggingInterceptor(isDev = true))
        connectTimeout(Var.CLIENT_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(Var.CLIENT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(Var.CLIENT_TIME_OUT, TimeUnit.SECONDS)
        followSslRedirects(true)
        followRedirects(true)
        retryOnConnectionFailure(true)
    }.build()
}

fun createHttpLoggingInterceptor(isDev: Boolean = true): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = if (isDev) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}

fun createMoshi(): Moshi {
    return Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
}