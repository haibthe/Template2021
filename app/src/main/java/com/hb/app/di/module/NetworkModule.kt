package com.hb.app.di.module

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hb.app.BuildConfig
import com.hb.base.TOKEN_TAG
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIME_OUT: Long = 60
private const val READ_TIME_OUT: Long = 60

val networkModule = module {
  single { providerGson() }
  single { BuildConfig.BASE_URL }
  single<Converter.Factory> { GsonConverterFactory.create(get()) }
  single { HttpLoggingInterceptor() }
  single<Interceptor> { providesHttpHeaderInterceptor(get()) }
  single { provideOkHttpClient(androidContext(), get(), get()) }
  single { provideRetrofitBuilder(get(), get()) }
}

fun providerGson(): Gson {
  val builder = GsonBuilder()
  builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
  return builder.create()
}

private fun providesHttpHeaderInterceptor(pref: SharedPreferences) = object : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()
    val token = pref.getString(TOKEN_TAG, "").orEmpty()
    val builder = original.newBuilder()
      .method(original.method, original.body)
      .addHeader("Authorization", token)
    val newRequest = builder.build()
    return chain.proceed(newRequest)
  }
}

fun provideOkHttpClient(
  context: Context,
  headerInterceptor: Interceptor,
  loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
  val builder = OkHttpClient.Builder()

  builder.addNetworkInterceptor(headerInterceptor)
  if (BuildConfig.DEBUG) {
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    builder.addNetworkInterceptor(loggingInterceptor)
  }
  val cacheSize = 10 * 1024 * 1024
  val cache = Cache(context.cacheDir, cacheSize.toLong())
  builder.cache(cache)
  builder.connectionPool(ConnectionPool(5, 5, TimeUnit.MINUTES))
  builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
  builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
  return builder.build()
}

fun provideRetrofitBuilder(
  client: OkHttpClient,
  converterFactory: Converter.Factory
): Retrofit.Builder {
  val builder = Retrofit.Builder()
  return builder.client(client)
    // .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
    // .addConverterFactory(NullOnEmptyConverterFactory())
    .addConverterFactory(converterFactory)
}
