package br.com.andersonsv.test.network.model.user

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class Avatar(
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
        val CREATOR: Parcelable.Creator<Avatar> = object : Parcelable.Creator<Avatar> {
            override fun createFromParcel(source: Parcel): Avatar = Avatar(source)
            override fun newArray(size: Int): Array<Avatar?> = arrayOfNulls(size)
        }
    }
}