package br.com.andersonsv.test.network.model.product

import android.os.Parcelable
import br.com.andersonsv.test.network.model.user.User
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Int,
    @field:Json(name = "discount_percentage")
    val discountPorcentage: Double,
    val title: String,
    val price: Double,
    @field:Json(name = "original_price")
    val originalPrice: Double,
    val size: String?,
    @field:Json(name = "likes_count")
    val likesCount: Int?,
    @field:Json(name = "maximum_installment")
    val maximumInstallment: Int?,
    @field:Json(name = "published_comments_count")
    val publishedCommentsCount: Int?,
    val content: String,

    val photos: MutableList<Photo>,

    val user: User

) : Parcelable