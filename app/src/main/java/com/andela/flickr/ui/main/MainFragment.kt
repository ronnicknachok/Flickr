package com.andela.flickr.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.andela.flickr.R
import com.andela.flickr.api.Constants
import com.andela.flickr.models.Photo
import com.andela.flickr.ui.adapters.MainAdapter
import com.andela.flickr.ui.detail.DetailedViewFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
	
	companion object {
		fun newInstance() = MainFragment()
	}
	
	private lateinit var adapter: MainAdapter
	private lateinit var viewModel: MainViewModel
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }
			?: MainViewModel()
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return inflater.inflate(R.layout.main_fragment, container, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mainRecyclerView.setHasFixedSize(true)
		mainRecyclerView.layoutManager = GridLayoutManager(context, 2)
		displayPhotos()
		setupQuerySearchBox()
	}
	
	private fun setupQuerySearchBox() {
		simpleSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String): Boolean {
				viewModel.getPhotosFromFlickr(query)
				return false
			}
			
			override fun onQueryTextChange(newText: String?): Boolean {
				return false
			}
			
		})
	}
	
	private fun displayPhotos() {
		viewModel.getPhotosFromFlickr(Constants.DEFAULT_SEARCH_TERM).observe(this, Observer {
			if (it.photos.photo.isNotEmpty()) {
				mainRecyclerView.visibility = View.VISIBLE
				tvNoResultsFound.visibility = View.GONE
				adapter = MainAdapter(it.photos.photo, object : MainAdapter.OnItemClickListener {
					override fun onItemClick(item: Photo) {
						viewModel.passPhotoDetails(item)
						activity?.supportFragmentManager?.beginTransaction()
							?.replace(R.id.container, DetailedViewFragment.newInstance())
							?.addToBackStack(null)
							?.commit()
					}
					
				})
				mainRecyclerView.adapter = adapter
			} else {
				mainRecyclerView.visibility = View.GONE
				tvNoResultsFound.visibility = View.VISIBLE
			}
		})
	}
}
