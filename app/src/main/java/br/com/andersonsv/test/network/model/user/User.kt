package br.com.andersonsv.test.network.model.user

import com.squareup.moshi.Json

data class User(
    @Json(name= "id")
    val id: Int,
    @Json(name= "name")
    val name: String,
    @Json(name="avatar")
    val avatar: Avatar
)