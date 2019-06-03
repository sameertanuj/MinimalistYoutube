package com.samoana.minimalistyoutube.di

import android.app.Application
import com.samoana.minimalistyoutube.repository.MYTService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideMYTService(client: OkHttpClient): MYTService {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MYTService::class.java)
    }

    @Provides
    @Singleton
    fun getOkHttpClient(application: Application): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor { chain ->
                val packageName = application.packageName
                val newRequest = chain.request().newBuilder()
                    .addHeader("X-Android-Package", packageName)
                    .addHeader("X-Android-Cert", "0F:73:16:1F:38:D6:0E:B5:8C:73:AA:DC:85:27:30:43:63:10:19:BA")
                    .build()
                chain.proceed(newRequest)
            }
        return builder.build()
    }
}