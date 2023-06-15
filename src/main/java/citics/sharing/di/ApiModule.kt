package citics.sharing.di

import citics.sharing.service.customadapter.NetworkResponseAdapterFactory
import citics.sharing.data.datasource.local.PreferenceManager
import citics.sharing.service.APIService
import citics.sharing.service.header.ApiHeadersProvider
import citics.sharing.session.CiticsBuildConfig
import com.sharing.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by ChinhQT on 25/09/2022.
 */
@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideAuthInterceptor(header: ApiHeadersProvider): AuthInterceptor {
        return AuthInterceptor(header)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        if (BuildConfig.DEBUG) {
            Timber.d("HttpLoggingInterceptor Debug")
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        Timber.d("HttpLoggingInterceptor Relase")
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(auth: AuthInterceptor, logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).addInterceptor(auth).addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkResponseAdapterFactory(): NetworkResponseAdapterFactory =
        NetworkResponseAdapterFactory()

    @ApiAgent
    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient, networkResponseAdapterFactory: NetworkResponseAdapterFactory
    ): Retrofit = Retrofit.Builder().baseUrl(CiticsBuildConfig.BASE_URL_API_MAIN)
        .client(client)
        .addCallAdapterFactory(networkResponseAdapterFactory).addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        ).build()

    @ApiAgent
    @Singleton
    @Provides
    fun provideApiService(@ApiAgent retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @ApiUploader
    @Singleton
    @Provides
    fun provideRetrofitForUploader(
        client: OkHttpClient, networkResponseAdapterFactory: NetworkResponseAdapterFactory
    ): Retrofit = Retrofit.Builder().baseUrl(CiticsBuildConfig.BASE_URL_API_UPLOADER).client(client)
        .addCallAdapterFactory(networkResponseAdapterFactory).addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        ).build()

    @ApiUploader
    @Singleton
    @Provides
    fun provideApiServiceForUploader(@ApiUploader retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @ApiAgentSearch
    @Singleton
    @Provides
    fun provideRetrofitForApiAgentSearch(
        client: OkHttpClient, networkResponseAdapterFactory: NetworkResponseAdapterFactory
    ): Retrofit = Retrofit.Builder().baseUrl(CiticsBuildConfig.BASE_URL_API_SEARCH).client(client)
        .addCallAdapterFactory(networkResponseAdapterFactory).addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        ).build()

    @ApiAgentSearch
    @Singleton
    @Provides
    fun provideApiServiceForApiAgentSearch(@ApiAgentSearch retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideApiHeaders(preferenceManager: PreferenceManager): ApiHeadersProvider {
        return ApiHeadersProvider(preferenceManager)
    }
}