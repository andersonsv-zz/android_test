package br.com.andersonsv.test.network.enjoei

import br.com.andersonsv.test.network.model.product.Product
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object EnjoeiAPI {

    val productApi : ProductApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-anon-0f74657a25-enjoeitest.apiary-mock.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return@lazy retrofit.create(ProductApi::class.java)
    }
}