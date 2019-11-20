package com.andela.flickr.api

import com.andela.flickr.models.FlickrModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
	
	/**
	 * This function is used to search for photos using the search tag that is provided
	 * by the user of the application.
	 */
	@GET("rest")
	fun searchPhotos(
		@Query("method") method: String,
		@Query("api_key") apiKey: String,
		@Query("tags") tags: String,
		@Query("per_page") perPage: Int,
		@Query("format") format: String,
		@Query("nojsoncallback") noJsonCallback: Int
	): Call<FlickrModel>
}