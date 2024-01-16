package id.andra.doqu_store.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaDto(
    @Json(name = "pagination")
    val pagination: PaginationDto
)

@JsonClass(generateAdapter = true)
data class PaginationDto(
    @Json(name = "total")
    val total: Int = 0,
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "per_page")
    val perPage: Int = 0,
    @Json(name = "current_page")
    val currentPage: Int = 0,
    @Json(name = "total_pages")
    val totalPages: Int = 0
)