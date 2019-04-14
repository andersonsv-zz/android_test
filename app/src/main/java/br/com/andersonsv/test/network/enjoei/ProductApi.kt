package br.com.andersonsv.test.network.enjoei

import br.com.andersonsv.test.network.model.product.HomeProducts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("products/home")
    fun getHomeProducts(
        @Query("page") page: Int
    ): Call<HomeProducts>
}