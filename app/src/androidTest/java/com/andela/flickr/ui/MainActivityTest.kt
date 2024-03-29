package com.andela.flickr.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.andela.flickr.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
	
	@Rule
	@JvmField
	var mActivityTestRule = ActivityTestRule(MainActivity::class.java)
	
	@Test
	fun mainActivityTest() {
		val appCompatImageView = onView(
			allOf(
				withId(R.id.search_button), withContentDescription("Search"),
				childAtPosition(
					allOf(
						withId(R.id.search_bar),
						childAtPosition(
							withId(R.id.simpleSearchView),
							0
						)
					),
					1
				),
				isDisplayed()
			)
		)
		appCompatImageView.perform(click())
		
		val linearLayoutCompat = onView(
			allOf(
				withId(R.id.simpleSearchView),
				childAtPosition(
					allOf(
						withId(R.id.main),
						childAtPosition(
							withId(R.id.container),
							0
						)
					),
					0
				),
				isDisplayed()
			)
		)
		linearLayoutCompat.check(matches(isDisplayed()))
		
		val recyclerView = onView(
			allOf(
				withId(R.id.mainRecyclerView),
				childAtPosition(
					allOf(
						withId(R.id.main),
						childAtPosition(
							withId(R.id.container),
							0
						)
					),
					1
				),
				isDisplayed()
			)
		)
		recyclerView.check(matches(isDisplayed()))
	}
	
	private fun childAtPosition(
		parentMatcher: Matcher<View>, position: Int
	): Matcher<View> {
		
		return object : TypeSafeMatcher<View>() {
			override fun describeTo(description: Description) {
				description.appendText("Child at position $position in parent ")
				parentMatcher.describeTo(description)
			}
			
			public override fun matchesSafely(view: View): Boolean {
				val parent = view.parent
				return parent is ViewGroup && parentMatcher.matches(parent)
						&& view == parent.getChildAt(position)
			}
		}
	}
}
