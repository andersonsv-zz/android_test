package br.com.andersonsv.test.network.model.product

import com.squareup.moshi.Json

data class Photo(
    @Json(name= "public_id")
    val publicId: String,
    @Json(name= "crop")
    val crop: String,
    @Json(name= "gravity")
    val gravity: String
)