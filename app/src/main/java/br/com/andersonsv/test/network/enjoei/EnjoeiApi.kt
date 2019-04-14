package br.com.andersonsv.test.network.enjoei

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class EnjoeiAPI {

    companion object {

        private var retrofit: Retrofit? = null

        val client: Retrofit
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create())
                        .baseUrl("https://private-anon-0f74657a25-enjoeitest.apiary-mock.com/")
                        .build()
                }
                return retrofit!!
            }
    }
}