package com.andela.flickr.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andela.flickr.R
import com.andela.flickr.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.main_activity)
		if (savedInstanceState == null) {
			supportFragmentManager.beginTransaction()
				.replace(R.id.container, MainFragment.newInstance())
				.commitNow()
		}
	}
	
}
