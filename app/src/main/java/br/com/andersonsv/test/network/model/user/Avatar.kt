package br.com.andersonsv.test.network.model.user

import com.squareup.moshi.Json

data class Avatar(
    @Json(name= "public_id")
    val publicId: String,
    @Json(name= "crop")
    val crop: String,
    @Json(name= "gravity")
    val gravity: String
)