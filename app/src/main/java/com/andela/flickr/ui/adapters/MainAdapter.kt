package com.andela.flickr.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andela.flickr.R
import com.andela.flickr.models.Photo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_photos.view.*

class MainAdapter(
	private val listOfPhotos: List<Photo>,
	private val listener: OnItemClickListener
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		LayoutInflater.from(parent.context)
			.inflate(R.layout.row_photos, parent, false)
			.run { ViewHolder(this, listener) }
	
	override fun getItemCount(): Int {
		return listOfPhotos.size
	}
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(listOfPhotos[position])
	}
	
	interface OnItemClickListener {
		fun onItemClick(item: Photo)
	}
	
	class ViewHolder(itemView: View, private val listener: OnItemClickListener) :
		RecyclerView.ViewHolder(itemView) {
		
		fun bind(item: Photo) {
			val photoUrl =
				"https://farm${item.farm}.staticflickr.com/${item.server}/${item.id}_${item.secret}.jpg"
			Glide.with(itemView.context)
				.load(photoUrl)
				.into(itemView.ivFlickrPicture)
			itemView.tvFlickPictureTitle.text = item.title
			itemView.setOnClickListener { listener.onItemClick(item) }
		}
	}
	
}