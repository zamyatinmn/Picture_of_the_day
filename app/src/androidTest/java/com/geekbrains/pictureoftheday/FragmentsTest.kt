package com.geekbrains.pictureoftheday

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.pictureoftheday.view.SettingsFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FragmentsTest {

    private lateinit var scenario: FragmentScenario<SettingsFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_PictureOfTheDay)
    }

    @Test
    fun settings_checkText() {
        val assertion = matches(withText("Settings"))
        onView(withId(R.id.settings_text)).check(assertion)
    }

    @Test
    fun settings_checkChipMoon() {
        onView(withId(R.id.themes_btn)).perform(ViewActions.click())
        val assertion = matches(isDisplayed())
        onView(withId(R.id.moon)).check(assertion)
    }

    @Test
    fun settings_checkChipMars() {
        onView(withId(R.id.themes_btn)).perform(ViewActions.click())
        val assertion = matches(isDisplayed())
        onView(withId(R.id.mars)).check(assertion)
    }

    @Test
    fun settings_checkChipMilkyWay() {
        onView(withId(R.id.themes_btn)).perform(ViewActions.click())
        val assertion = matches(isDisplayed())
        onView(withId(R.id.milkyWay)).check(assertion)
    }
}