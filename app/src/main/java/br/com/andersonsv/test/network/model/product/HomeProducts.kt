package br.com.andersonsv.test.network.model.product

import com.squareup.moshi.Json

data class HomeProducts(
    @field:Json(name= "pagination")
    val pagination: Pagination,
    @field:Json(name="products")
    val products: List<Product>?
)


