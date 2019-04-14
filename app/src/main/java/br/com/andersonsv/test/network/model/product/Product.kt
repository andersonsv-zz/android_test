package br.com.andersonsv.test.network.model.product

import br.com.andersonsv.test.network.model.user.User
import com.squareup.moshi.Json

data class Product(
    @Json(name= "id")
    val id: Int,
    @Json(name="discount_percentage")
    val discountPorcentage: Double,
    @Json(name="title")
    val title: String,
    @Json(name="price")
    val price: Double,
    @Json(name="original_price")
    val originalPrice: Double,
    @Json(name="size")
    val size: String?,
    @Json(name="likes_count")
    val likesCount: Int?,
    @Json(name="maximum_installment")
    val maximumInstallment: Int?,
    @Json(name="published_comments_count")
    val publishedCommentsCount: Int?,
    @Json(name="content")
    val content: String,

    @Json(name="photos")
    val photos: List<Photo>,
    @Json(name="user")
    val user: User
)