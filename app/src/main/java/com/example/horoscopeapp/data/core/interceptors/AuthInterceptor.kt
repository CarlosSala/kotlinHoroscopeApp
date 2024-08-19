package com.example.horoscopeapp.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        // request to server, newBuilder add data ot the header
        val request = chain.request().newBuilder()
            .header("Authorization", tokenManager.getToken()).build()
        return chain.proceed(request)
    }
}

// this function get token from server
class TokenManager @Inject constructor() {
    fun getToken(): String = ""

}