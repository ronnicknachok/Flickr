package com.andela.flickr.models


import com.google.gson.annotations.SerializedName

data class FlickrModel(
	@SerializedName("photos")
	val photos: Photos = Photos(),
	@SerializedName("stat")
	val stat: String = ""
)