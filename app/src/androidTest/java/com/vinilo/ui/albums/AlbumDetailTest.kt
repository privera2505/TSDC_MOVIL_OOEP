package com.vinilo.ui.albums

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
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
class AlbumDetailTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // Simula clic en el botón (ajusta el ID a tu layout real)
        onView(withId(R.id.albumsFragment)).perform(click())

        // Verifica que se muestra el título de la sección de álbumes
        onView(withId(R.id.appTitle))
            .check(matches(isDisplayed()))

        //Ir al detalle del primer elemento
        onView(withId(R.id.recycler_albums))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
            )
    }

    @Test
    fun verifyDetailElements() {
        // Verifica que se muestran los elementos principales del detalle de Album
        onView(withId(R.id.albumSectionTitle))
            .check(matches(isDisplayed()))

        onView(withId(R.id.nameAlbumName))
            .check(matches(isDisplayed()))

        onView(withId(R.id.genraText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.imageAlbumCoverDescription))
            .check(matches(isDisplayed()))

        onView(withId(R.id.releaseDate))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tracksAlbumDescription))
            .check(matches(isDisplayed()))

        onView(withId(R.id.titleTracksSection))
            .check(matches(isDisplayed()))
    }

    @Test
    fun comeBackToTheList() {
        // Clic en botón para volver (ajusta el ID si es diferente)
        onView(withId(R.id.backBtnAlbum)).perform(click())

        // Verifica que volvimos al listado
        onView(withId(R.id.recycler_albums)).check(matches(isDisplayed()))
    }

}
