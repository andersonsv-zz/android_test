package br.com.andersonsv.test.network.enjoei

import android.content.Context
import br.com.andersonsv.test.util.ConnectivityInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class EnjoeiAPI constructor(mContext: Context) {

    private var client = OkHttpClient.Builder()
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