package com.vinilo.ui.performers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vinilo.view.MainActivity
import com.vinilo.view.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed


@RunWith(AndroidJUnit4::class)
class PerformerDetailTest {

    var TitleList : String = ""

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        //Ir al fragmento de los artistas
        onView(withId(R.id.artistsFragment)).perform(click())
        //Esperar a que se carguen los elementos de los artistas.
        Thread.sleep(3000)
        //Ir al detalle del primer elemento
        val recycler = onView(withId(R.id.recycler_performers))

        recycler.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                object : ViewAction {
                    override fun getConstraints() = null
                    override fun getDescription() = "Obtener texto del nombre del artista"
                    override fun perform(uiController: UiController?, view: View?) {
                        val textView = view?.findViewById<TextView>(R.id.performerName)
                        TitleList = textView?.text.toString()
                    }
                }
            )
        )

        recycler.perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
        )

    }

    @Test
    fun verifyDetailElements() {
        //Verifica que se muestran los elementos del detalle
        //Primero esperar un tiempo
        Thread.sleep(2000)

        //Titulo del detalle
        onView(withId(R.id.albumSectionTitle)).check(matches(isDisplayed()))

        //Imagen del artista
        onView(withId(R.id.imgArtistPhoto)).check(matches(isDisplayed()))

        //Nombre del artista
        onView(withId(R.id.tvArtistName)).check(matches(isDisplayed()))

        //Descripcion del artista
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))

        //Lista de albumes
        onView(withId(R.id.rvAlbums)).check(matches(isDisplayed()))


    }

    @Test
    fun compareTitleListAndDetail() {
        Thread.sleep(2000)

        var Name = ""

        onView(withId(R.id.tvArtistName)).check{ view, _ ->
            Name =  (view as TextView).text.toString()
        }

        assert(TitleList == Name)
    }

    @Test
    fun validDescriptionAndNameIsNotEmpty() {
        Thread.sleep(2000)
        //Nombre
        var name = ""

        onView(withId(R.id.tvArtistName)).check{ view, _ ->
            name = (view as TextView).text.toString()
            assert(name.isNotBlank())
        }

        //Descripción
        var Descripcion = ""
        onView(withId(R.id.tvDescription)).check{ view, _ ->
            Descripcion = (view as TextView).text.toString()
            assert(Descripcion.isNotBlank())
        }

    }



}