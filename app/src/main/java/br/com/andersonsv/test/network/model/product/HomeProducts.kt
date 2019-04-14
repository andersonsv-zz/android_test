package br.com.andersonsv.test.network.model.product

import com.squareup.moshi.Json

data class HomeProducts(
    @Json(name= "pagination")
    val pagination: List<Pagination>?,
    @Json(name="products")
    val products: List<Product>?
)


