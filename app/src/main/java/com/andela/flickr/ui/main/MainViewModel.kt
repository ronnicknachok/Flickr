package com.andela.flickr.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andela.flickr.api.Constants
import com.andela.flickr.api.FlickrApi
import com.andela.flickr.api.FlickrRetrofit
import com.andela.flickr.models.FlickrModel
import com.andela.flickr.models.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
	private var photoDetail: MutableLiveData<Photo> = MutableLiveData()
	private var photoInfo: MutableLiveData<FlickrModel> = MutableLiveData()
	private val service = FlickrRetrofit.createService(FlickrApi::class.java)
	
	private fun searchPhotosUsingFlickrApi(searchTerm: String) {
		val call = service.searchPhotos(
			"flickr.photos.search",
			Constants.FLICKR_API_KEY,
			searchTerm,
			25,
			"json",
			1
		)
		
		call.enqueue(object : Callback<FlickrModel> {
			override fun onFailure(call: Call<FlickrModel>, t: Throwable) {
				Log.d("Photo Loading Error: ", t.message)
			}
			
			override fun onResponse(call: Call<FlickrModel>, response: Response<FlickrModel>) {
				if (response.isSuccessful) {
					photoInfo.value = response.body()
					Log.d("Photo Information is: ", response.body().toString())
				}
			}
			
		})
	}
	
	/**
	 * This function is used to initiate searching of photos */
	fun getPhotosFromFlickr(searchTerm: String): LiveData<FlickrModel> {
		searchPhotosUsingFlickrApi(searchTerm)
		return photoInfo
	}
	
	/**
	 * This function is used to pass photo details to other fragments
	 * */
	fun passPhotoDetails(details: Photo) {
		photoDetail.value = details
	}
	
	/** This function is used to get the details of photos passed to fragments
	 * */
	fun getPhotoDetails(): LiveData<Photo> {
		return photoDetail
	}
}
