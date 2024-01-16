package id.andra.doqu_store.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import id.andra.doqu_store.domain.model.Product

@JsonClass(generateAdapter = true)
data class ProductDto(
    @Json(name = "data") val data: List<ProductData> = listOf(),
    @Json(name = "meta") val meta: MetaDto
)

@JsonClass(generateAdapter = true)
data class ProductData(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "icon") val icon: String?,
    @Json(name = "created_at") val createdAt: String?,
    @Json(name = "created_by") val createdBy: Int?,
    @Json(name = "created_by_name") val createdByName: String?,
    @Json(name = "updated_at") val updatedAt: String?,
    @Json(name = "updated_by") val updatedBy: Int?,
    @Json(name = "updated_by_name") val updatedByName: String?,
    @Json(name = "deleted_at") val deletedAt: String?
) {
    fun toProduct(): Product {
        return Product(
            id = id ?: 0,
            name = name.orEmpty(),
            description = description.orEmpty(),
            icon = icon.orEmpty(),
            createdAt = createdAt.orEmpty(),
            createdBy = createdBy ?: 0,
            createdByName = createdByName.orEmpty(),
            updatedAt = updatedAt.orEmpty(),
            updatedBy = updatedBy ?: 0,
            updatedByName = updatedByName.orEmpty(),
            deletedAt = deletedAt.orEmpty(),
        )
    }
}
