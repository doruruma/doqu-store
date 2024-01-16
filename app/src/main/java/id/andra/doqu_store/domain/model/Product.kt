package id.andra.doqu_store.domain.model

data class Product(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val icon: String = "",
    val createdAt: String = "",
    val createdBy: Int = 0,
    val createdByName: String = "",
    val updatedAt: String = "",
    val updatedBy: Int = 0,
    val updatedByName: String = "",
    val deletedAt: String = ""
)
