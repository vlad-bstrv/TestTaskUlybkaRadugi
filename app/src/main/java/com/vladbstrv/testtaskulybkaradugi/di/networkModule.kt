package com.vladbstrv.testtaskulybkaradugi.di

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vladbstrv.testtaskulybkaradugi.R
import com.vladbstrv.testtaskulybkaradugi.data.retrofit.DataApi
import com.vladbstrv.testtaskulybkaradugi.data.retrofit.RetrofitRepositoryImpl
import com.vladbstrv.testtaskulybkaradugi.domain.Repository
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_URL = "http://api-test.tdera.ru/"

val networkModule = module {
    single<Repository> { RetrofitRepositoryImpl(api = get()) }

    single<DataApi> { get<Retrofit>().create(DataApi::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header(
                        "Authorization",
                        get(named("basic"))
                    )
                    .build()
                return@addInterceptor chain.proceed(request)
            }
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    factory(named("login")) {
//        "l12345678"
        androidContext().getSharedPreferences(androidContext().getString(R.string.app_name), Context.MODE_PRIVATE)
            .getString("LOGIN", "")
    }
    factory(named("password")) {
//        "p12345678"
        androidContext().getSharedPreferences(
            androidContext().getString(R.string.app_name),
            Context.MODE_PRIVATE
        ).getString("PASSWORD", "")
    }
    factory(named("basic")) {
        Credentials.basic(
            get(named("login")),
            get(named("password"))
        )
    }
}