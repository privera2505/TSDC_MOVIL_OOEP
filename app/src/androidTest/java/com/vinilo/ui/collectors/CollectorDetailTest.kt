package com.vinilo.ui.collectors

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vinilo.view.MainActivity
import com.vinilo.view.R
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.espresso.IdlingRegistry
import com.vinilo.utils.RecyclerViewItemCountAssertion
import com.vinilo.utils.RecyclerViewItemCountIdlingResource
import org.junit.After
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class CollectorDetailTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        onView(withId(R.id.collectorsFragment)).perform(click())
        onView(withId(R.id.titleFragmentCollector))
            .check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.recycler_collectors)
            val idlingResource = RecyclerViewItemCountIdlingResource(recyclerView)
            IdlingRegistry.getInstance().register(idlingResource)
        }

        onView(withId(R.id.recycler_collectors))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
            )

        activityRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.recycler_collector_comments)
            val idlingResource = RecyclerViewItemCountIdlingResource(recyclerView)
            IdlingRegistry.getInstance().register(idlingResource)
        }
    }

    @Test
    fun clickOnCollectorItem_opensDetailScreen() {
        onView(withId(R.id.detailCollectorTitle))
            .check(matches(isDisplayed()))
    }

    @Test
    fun validate_main_elements() {
        onView(withId(R.id.detailCollectorTitle))
            .check(matches(isDisplayed()))

        onView(withId(R.id.detailCollectorImage))
            .check(matches(isDisplayed()))

        onView(withId(R.id.detailCollectorTitleInfo))
            .check(matches(isDisplayed()))

        onView(withId(R.id.detailCollectorFavoritePerformers))
            .check(matches(isDisplayed()))

        onView(withId(R.id.recycler_collector_comments))
            .check(matches(isDisplayed()))

        onView(withId(R.id.recycler_collector_favorite_performer))
            .check(matches(isDisplayed()))
    }

    @Test
    fun comeBackToTheList() {
        onView(withId(R.id.backBtnCollectors)).perform(click())

        onView(withId(R.id.recycler_collectors)).check(matches(isDisplayed()))
    }

    @Test
    fun collector_has_comments() {
        onView(withId(R.id.recycler_collector_comments))
            .check(RecyclerViewItemCountAssertion(1))
    }

    @Test
    fun collector_has_favorite_performer() {

        onView(withId(R.id.recycler_collector_favorite_performer))
            .check(RecyclerViewItemCountAssertion(1))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().resources.forEach {
            IdlingRegistry.getInstance().unregister(it)
        }
    }
}