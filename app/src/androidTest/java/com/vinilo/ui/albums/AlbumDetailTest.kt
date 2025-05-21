package com.vinilo.ui.albums

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import com.vinilo.utils.RecyclerViewItemCountIdlingResource
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
        onView(withId(R.id.albumsFragment)).perform(click())
        onView(withId(R.id.appTitle))
            .check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.recycler_albums)
            val idlingResource = RecyclerViewItemCountIdlingResource(recyclerView)
            IdlingRegistry.getInstance().register(idlingResource)
        }

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

    @Test
    fun compareTitleListAndDetail() {
        // Clic en botón para volver (ajusta el ID si es diferente)
        onView(withId(R.id.backBtnAlbum)).perform(click())

        //Obtener el titulo del elemento en la lista al que se le va a hacer click
        var albumListName = ""
        var albumName = ""

        onView(withId(R.id.recycler_albums))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    object : ViewAction {
                        override fun getConstraints() = null
                        override fun getDescription() = "Obtener texto del nombre del álbum"
                        override fun perform(uiController: UiController?, view: View?) {
                            val textView = view?.findViewById<TextView>(R.id.albumName)
                            albumListName = textView?.text.toString()
                        }
                    }
                )
            )

        onView(withId(R.id.recycler_albums))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
            )


        //Obtener el título del detalle
        onView(withId(R.id.nameAlbumName)).check { view, _ ->
            albumName = (view as TextView).text.toString()
        }

        assert(albumListName == albumName) { "Titulos diferentes: $albumName, $albumListName" }
    }

    @Test
    fun desciptionHasNotBeEmpty() {
        onView(withId(R.id.tracksAlbumDescription)).check { view, _ ->
            val descripcion = (view as TextView).text.toString()
            assert(descripcion.isNotBlank()) { "La descripción del álbum está vacía" }
        }
    }

    @Test
    fun validateReleaseDateFormat() {
        onView(withId(R.id.releaseDate)).check { view, _ ->
            val releaseDate = (view as TextView).text.toString()
            val regex = Regex("""\d{2} \w{3} \d{4}""")
            assert(regex.matches(releaseDate)) { "Formato de fecha inválido: $releaseDate" }
        }
    }

    @Test
    fun validGenra() {
        val permitGenre: List<String> = listOf("Salsa", "Rock", "Pop", "Jazz")

        onView(withId(R.id.genraText)).check { view, _ ->
            val genero = (view as TextView).text.toString()
            assert(permitGenre.contains(genero)) { "Género inesperado: $genero" }
        }
    }

}
