package br.com.andersonsv.test.network.model.user

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Avatar(
    @field:Json(name = "public_id")
    val publicId: String,
    val crop: String,
    val gravity: String
) : Parcelable