package br.com.andersonsv.test.network.model.product

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class Photo(
    @field:Json(name = "public_id")
    val publicId: String,
    @field:Json(name = "crop")
    val crop: String,
    @field:Json(name = "gravity")
    val gravity: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(publicId)
        writeString(crop)
        writeString(gravity)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Photo> = object : Parcelable.Creator<Photo> {
            override fun createFromParcel(source: Parcel): Photo = Photo(source)
            override fun newArray(size: Int): Array<Photo?> = arrayOfNulls(size)
        }
    }
}