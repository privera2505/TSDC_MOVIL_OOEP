package com.vinilo.ui.awards

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
import org.hamcrest.Matchers.not
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class AwardCreateTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun goAwardCreateFragment(){
        //Ir al fragmento del premio
        onView(withId(R.id.awardsFragment)).perform(click())
        //Ir al fragmento de crear premio
        onView(withId(R.id.addButtonPrize)).perform(click())
    }

    @Test
    fun testEmptyEditTexts_ButtonDisabled(){
        onView(withId(R.id.btnCreatePrize)).check(matches(not(isEnabled())))
    }

    @Test
    fun testEditTextWithString_ButtonEnabled(){
        fillEditText()

        onView(withId(R.id.btnCreatePrize)).check(matches(isEnabled()))

    }

    @Test
    fun testEditTextWithString_SubmitForm(){
        fillEditText()
        onView(withId(R.id.btnCreatePrize)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.CreateSuccessAward)).check(matches(isDisplayed()))
    }

    fun fillEditText(){
        val textoAleatorio = UUID.randomUUID().toString().substring(0, 8)

        onView(withId(R.id.inputPrizeName)).perform(typeText(textoAleatorio), closeSoftKeyboard())
        onView(withId(R.id.inputPrizeDescription)).perform(typeText(textoAleatorio), closeSoftKeyboard())
        onView(withId(R.id.inputPrizeOrganization)).perform(typeText(textoAleatorio), closeSoftKeyboard())
    }

}