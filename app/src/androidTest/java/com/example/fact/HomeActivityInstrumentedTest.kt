package com.example.fact

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.fact.view.home.HomeActivity
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * Created by Lalit Khandelwal on 12, December, 2018.
 * lalitkhandelwal99@gmail.com
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityInstrumentedTest {
    private var recyclerViewMatcher: RecyclerViewMatcher? = null

    @get:Rule
    var homeActivity: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java, false, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        recyclerViewMatcher = RecyclerViewMatcher(R.id.factRecyclerView)
        homeActivity.launchActivity(null)
        IdlingRegistry.getInstance().register(homeActivity.activity.idlingResource)
        homeActivity.activity.idlingResource.setIdleState(false)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(homeActivity.activity.idlingResource)
    }

    /*
    * Test if the recyclerView content is populated with the desired data on Successful data load
    * */
    @Test
    fun assertScreenStateSuccess() {

        Thread.sleep(5000)

        onView(withId(R.id.toolBar)).check(matches(isDisplayed()))

        onView(
            withRecyclerView(R.id.factRecyclerView)
                .atPositionOnView(0, R.id.titleTextView)
        )
            .check(matches(withText("Beavers")))

        onView(
            withRecyclerView(R.id.factRecyclerView)
                .atPositionOnView(0, R.id.factImageView)
        )
            .check(matches(isDisplayed()))

        onView(
            withRecyclerView(R.id.factRecyclerView)
                .atPositionOnView(0, R.id.descriptionTextView)
        )
            .check(matches(isDisplayed()))

    }

    /*
    * Test if the recycler is present and nothing gets populated until the request is in progress
    * */
    @Test
    fun assertScreenStateWhileInProgress() {

        onView(withId(R.id.toolBar)).check(matches(isDisplayed()))

        onView(withId(R.id.factRecyclerView)).check(matches(isDisplayed()))

        assertThat(getRecyclerViewCount(), `is`(0))
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun getRecyclerViewCount(): Int {
        val recyclerView = homeActivity.activity.findViewById(R.id.factRecyclerView) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }
}
