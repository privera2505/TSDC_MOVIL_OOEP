package com.vinilo.ui.collectors

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vinilo.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.vinilo.view.R
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.junit.Assert.assertTrue
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class CollectorTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun goCollectorFragment() {
        //Ir al fragmento de la lista de los collecionistas
        onView(withId(R.id.collectorsFragment)).perform(click())
    }

    @Test
    fun verifyTitleListCollector(){
        val collectorTitle = onView(withId(R.id.titleFragmentCollector))

        collectorTitle.check(matches(isDisplayed()))

        collectorTitle.check(matches(withText("Coleccionistas")))
    }

    @Test
    fun verifyListCollectors() {
        onView(withId(R.id.recycler_collectors)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerCollector_hasItems(){
        activityRule.scenario.onActivity {
            Thread.sleep(6000)

            val rv = it.findViewById<RecyclerView>(R.id.recycler_collectors)
            val adaptador = rv.adapter

            //Cantidad de items
            val iTemsQuantity = adaptador?.itemCount ?: 0

            assertTrue(iTemsQuantity>0)

        }
    }

}