package br.com.andersonsv.test.network.model.product

import br.com.andersonsv.test.network.model.user.User
import com.squareup.moshi.Json
data class Product(
    @field:Json(name= "id")
    val id: Int,
    @field:Json(name="discount_percentage")
    val discountPorcentage: Double,
    @field:Json(name="title")
    val title: String,
    @field:Json(name="price")
    val price: Double,
    @field:Json(name="original_price")
    val originalPrice: Double,
    @field:Json(name="size")
    val size: String?,
    @field:Json(name="likes_count")
    val likesCount: Int?,
    @field:Json(name="maximum_installment")
    val maximumInstallment: Int?,
    @field:Json(name="published_comments_count")
    val publishedCommentsCount: Int?,
    @field:Json(name="content")
    val content: String,

    @field:Json(name="photos")
    val photos: MutableList<Photo>,
    @field:Json(name="user")
    val user: User
)