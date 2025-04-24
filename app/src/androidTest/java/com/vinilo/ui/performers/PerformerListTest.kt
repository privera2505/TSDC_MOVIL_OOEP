package com.vinilo.ui.performers

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vinilo.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import com.vinilo.view.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import org.junit.Test
import org.junit.Assert.assertTrue
import androidx.test.espresso.UiController
import android.view.View
import android.widget.TextView
import org.junit.Assert.assertEquals

@RunWith(AndroidJUnit4::class)
class PerformerListTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun goPermorfersFragment() {
        //Ir al fragmento de los artistas
        onView(withId(R.id.artistsFragment)).perform(click())
    }

    @Test
    fun verifyTitleListPerformers() {
        val artistTitle = onView(withId(R.id.artistTitle))
        //Verificar que el titulo del fragmento de artistas esta mostrado
        artistTitle.check(matches(isDisplayed()))
        //Verificar que el titulo del fragmento de artistas es "Artistas"
        artistTitle.check(matches(withText("Artistas")))
    }

    @Test
    fun verifyListPerformers() {
        //Verificar si la lista de performers es visible
        onView(withId(R.id.recycler_performers)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerPerformers_hasItems() {
        activityRule.scenario.onActivity {
            val rv = it.findViewById<RecyclerView>(R.id.recycler_performers)
            val adaptador = rv.adapter

            //Cantidad de items
            val iTemsQuantity = adaptador?.itemCount ?: 0

            assertTrue(iTemsQuantity>0)
        }
    }

    @Test
    fun titleAndImage_IsDisplayed() {
        //Ir a la posición
        onView(withId(R.id.recycler_performers))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
            )

        //Verificar que recyclerView esta visible
        onView(withId(R.id.recycler_performers)).check(matches(isDisplayed()))

        //Verificar que el texto esta visible
        onView(withId(R.id.recycler_performers))
            .check(matches(hasDescendant(withId(R.id.performerName))))

        //Verificar que la imagen esta visible
        onView(withId(R.id.recycler_performers))
            .check(matches(hasDescendant(withId(R.id.perfomerImage))))

    }

}