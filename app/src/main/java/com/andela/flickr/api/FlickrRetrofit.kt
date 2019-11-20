package com.andela.flickr.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class FlickrRetrofit {
	companion object {
		
		private val builder = Retrofit.Builder()
			.baseUrl(Constants.BACKEND_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.addConverterFactory(ScalarsConverterFactory.create())
		private var retrofit = builder.build()
		private val httpClient = OkHttpClient.Builder()
		private val logging = HttpLoggingInterceptor()
			.setLevel(HttpLoggingInterceptor.Level.BODY)
		
		/** This is a function that connects to the backend url without any headers. Used for all
		 * open endpoints that do not require a headers passed */
		fun <S> createService(serviceClass: Class<S>): S {
			if (!httpClient.interceptors().contains(logging)) {
				httpClient.addInterceptor(logging)
				builder.client(httpClient.build())
				retrofit = builder.build()
			}
			return retrofit.create(serviceClass)
		}
	}
}