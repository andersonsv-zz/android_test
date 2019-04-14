package br.com.andersonsv.test.network.model.user

import com.squareup.moshi.Json

data class User(
    @field:Json(name= "id")
    val id: Int,
    @field:Json(name= "name")
    val name: String,
    @field:Json(name="avatar")
    val avatar: Avatar
)