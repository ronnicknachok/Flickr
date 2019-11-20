package com.andela.flickr.models


import com.google.gson.annotations.SerializedName

data class Photos(
	@SerializedName("page")
	val page: Int = 0,
	@SerializedName("pages")
	val pages: Int = 0,
	@SerializedName("perpage")
	val perpage: Int = 0,
	@SerializedName("photo")
	val photo: List<Photo> = listOf(),
	@SerializedName("total")
	val total: String = ""
)