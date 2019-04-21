package br.com.andersonsv.test.network.enjoei

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import br.com.andersonsv.test.util.ConnectivityInterceptor
import okhttp3.OkHttpClient


class EnjoeiAPI constructor(val mContext: Context) {

    var client = OkHttpClient.Builder()
        .addInterceptor(ConnectivityInterceptor(mContext))
        .build()

    val productApi : ProductApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-anon-0f74657a25-enjoeitest.apiary-mock.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        return@lazy retrofit.create(ProductApi::class.java)
    }
}