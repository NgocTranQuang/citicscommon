package citics.sharing.di

import citics.sharing.service.header.ApiHeadersProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(val apiHeadersProvider: ApiHeadersProvider) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        apiHeadersProvider.getAuthenticatedHeaders().forEach {
            requestBuilder.addHeader(it.key, it.value)
        }
        return chain.proceed(requestBuilder.build())
    }
}