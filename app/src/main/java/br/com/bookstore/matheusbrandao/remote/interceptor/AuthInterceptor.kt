package br.com.bookstore.matheusbrandao.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(putAuthorizationHeaderInRequest(chain.request()))
    }

    private fun putAuthorizationHeaderInRequest(request: Request): Request {
        val builder = request.newBuilder()

        builder.addHeader("Content-type", "application/json")
        builder.method(request.method(), request.body())
        return builder.build()
    }
}