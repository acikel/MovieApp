package com.example.crewmovies.core.data.di

import android.util.Log
import com.example.crewmovies.core.data.BuildConfig
import com.example.crewmovies.core.data.apiservice.MovieService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import tech.thdev.network.flowcalladapterfactory.FlowCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger
import javax.inject.Named
import javax.inject.Singleton

//Sets up the network connection via OkHttpClient with Gson and Retrofit when an MovieService is
//generated via dependency injection.
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        //Use this logging interceptor only for debbuging delete afterwards as it can leak information to anyone with a physicla device.
        //The if check if(BuildConfig.DEBUG) allows the logger only in development mode and dosent use the user for builds.
        //If the logs are too long it can effect performance in development mode.
        if(BuildConfig.DEBUG){
            okHttpBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            /* if setLevel doesn't work use this:
            val logging = HttpLoggingInterceptor()

            logging.level = (HttpLoggingInterceptor.Level.BASIC)
            okHttpBuilder.addInterceptor(logging)
             */
        }

        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    //@Named("auth_retrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    companion object {
        var baseUrl = "https://api.themoviedb.org/"
    }
}