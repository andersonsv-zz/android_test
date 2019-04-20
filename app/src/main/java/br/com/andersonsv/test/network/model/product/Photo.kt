package br.com.andersonsv.test.network.model.product

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @field:Json(name = "public_id")
    val publicId: String,
    val crop: String,
    val gravity: String
) : Parcelable