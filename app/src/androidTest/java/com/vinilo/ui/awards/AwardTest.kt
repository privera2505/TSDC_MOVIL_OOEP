package com.vinilo.ui.awards

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vinilo.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import com.vinilo.view.R
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import org.junit.Test
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class AwardTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun goAwardFragment(){
        //Ir al fragmento del premio
        onView(withId(R.id.awardsFragment)).perform(click())
    }

    @Test
    fun verifyListAwards(){
        onView(withId(R.id.recycler_awards)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerAwards_hasItems(){
        activityRule.scenario.onActivity {
            Thread.sleep(6000)

            val rv = it.findViewById<RecyclerView>(R.id.recycler_awards)
            val adaptador = rv.adapter

            //Cantidad de items
            val iTemsQuantity = adaptador?.itemCount ?: 0

            assertTrue(iTemsQuantity>0)

        }
    }

}