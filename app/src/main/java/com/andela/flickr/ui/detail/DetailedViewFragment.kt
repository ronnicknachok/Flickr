package com.andela.flickr.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andela.flickr.R
import com.andela.flickr.ui.main.MainViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detailed_view.*

class DetailedViewFragment : Fragment() {
	
	companion object {
		fun newInstance() = DetailedViewFragment()
	}
	
	private lateinit var viewModel: MainViewModel
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }
			?: MainViewModel()
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_detailed_view, container, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		displayDetailedViewOfPhoto()
	}
	
	private fun displayDetailedViewOfPhoto() {
		viewModel.getPhotoDetails().observe(this, Observer {
			val photoUrl =
				"https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
			
			context?.let { context ->
				Glide.with(context)
					.load(photoUrl)
					.into(ivFlickrPicture)
			}
			
			tvFlickPictureTitle.text = it.title
		})
	}
}
