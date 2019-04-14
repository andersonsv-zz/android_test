package br.com.andersonsv.test.network.model.user

import com.squareup.moshi.Json

data class Avatar(
    @field:Json(name= "public_id")
    val publicId: String,
    @field:Json(name= "crop")
    val crop: String,
    @field:Json(name= "gravity")
    val gravity: String
)