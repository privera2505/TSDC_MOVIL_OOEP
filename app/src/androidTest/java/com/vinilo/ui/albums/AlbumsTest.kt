package com.vinilo.ui.albums

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import com.vinilo.view.MainActivity
import com.vinilo.view.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumsTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Simula clic en el botón (ajusta el ID a tu layout real)
        onView(withId(R.id.albumsFragment)).perform(click())
    }

    @Test
    fun verifyMainElements() {
        // Verifica que se muestran los elementos principales de la panatlla de Albums
        onView(withId(R.id.menuButton))
            .check(matches(isDisplayed()))

        onView(withId(R.id.appTitle))
            .check(matches(isDisplayed()))

        onView(withId(R.id.searchButton))
            .check(matches(isDisplayed()))

        onView(withId(R.id.search_box))
            .check(matches(isDisplayed()))

        onView(withId(R.id.albumListTitle))
            .check(matches(isDisplayed()))
    }

    @Test
    fun verifyRecyclerView() {
        // Verifica que el RecyclerView se muestra y tiene elementos
        onView(withId(R.id.recycler_albums))
            .check(matches(isDisplayed()))

        onView(withId(R.id.recycler_albums))
            .check(matches(hasMinimumChildCount(1)))
    }

    @Test
    fun verifyAlbumItemClick() {
        // Verifica que al hacer clic en un elemento del RecyclerView, se abre la pantalla de detalle
        onView(withId(R.id.recycler_albums))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
            )

        // Verifica que se muestra el título de la sección de álbumes
        onView(withId(R.id.albumSectionTitle))
            .check(matches(isDisplayed()))
    }

}