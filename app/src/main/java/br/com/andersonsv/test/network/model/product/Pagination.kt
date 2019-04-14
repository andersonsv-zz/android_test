package br.com.andersonsv.test.network.model.product

import com.squareup.moshi.Json

data class Pagination(
    @field:Json(name= "current_page")
    val currentPage: Int,
    @field:Json(name="total_pages")
    val totalPages: Int
)