package com.nikhil.movietime.core.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetryInterceptor(private val maxRetry: Int = 3) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var attempt = 0
        var exception: IOException? = null

        while (attempt < maxRetry) {
            try {
                return chain.proceed(chain.request())
            } catch (e: IOException) {
                exception = e
                attempt++
                if (attempt >= maxRetry) break
            }
        }

        throw exception ?: IOException("Unknown network error")
    }
}
