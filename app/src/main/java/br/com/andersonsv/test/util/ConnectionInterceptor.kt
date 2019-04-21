package br.com.andersonsv.test.util

import android.content.Context
import br.com.andersonsv.test.extension.NoConnectivityException
import br.com.andersonsv.test.extension.isNetworkConnected
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val mContext: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!mContext.isNetworkConnected()) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

}