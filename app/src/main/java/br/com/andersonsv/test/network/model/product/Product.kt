package br.com.andersonsv.test.network.model.product

import android.os.Parcel
import android.os.Parcelable
import br.com.andersonsv.test.network.model.user.User
import com.squareup.moshi.Json

data class Product(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "discount_percentage")
    val discountPorcentage: Double,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "price")
    val price: Double,
    @field:Json(name = "original_price")
    val originalPrice: Double,
    @field:Json(name = "size")
    val size: String?,
    @field:Json(name = "likes_count")
    val likesCount: Int?,
    @field:Json(name = "maximum_installment")
    val maximumInstallment: Int?,
    @field:Json(name = "published_comments_count")
    val publishedCommentsCount: Int?,
    @field:Json(name = "content")
    val content: String,

    @field:Json(name = "photos")
    val photos: MutableList<Photo>,

    @field:Json(name = "user")
    val user: User

) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readDouble(),
        source.readString(),
        source.readDouble(),
        source.readDouble(),
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        ArrayList<Photo>().apply { source.readList(this, Photo::class.java.classLoader) },
        source.readParcelable<User>(User::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeDouble(discountPorcentage)
        writeString(title)
        writeDouble(price)
        writeDouble(originalPrice)
        writeString(size)
        writeValue(likesCount)
        writeValue(maximumInstallment)
        writeValue(publishedCommentsCount)
        writeString(content)
        writeList(photos)
        writeParcelable(user, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Product> = object : Parcelable.Creator<Product> {
            override fun createFromParcel(source: Parcel): Product = Product(source)
            override fun newArray(size: Int): Array<Product?> = arrayOfNulls(size)
        }
    }
}
