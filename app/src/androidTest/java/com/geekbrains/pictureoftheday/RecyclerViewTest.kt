package com.geekbrains.pictureoftheday

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.pictureoftheday.view.recycler.RecyclerAdapter
import com.geekbrains.pictureoftheday.view.recycler.RecyclerFragment
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecyclerViewTest {
    private lateinit var scenario: FragmentScenario<RecyclerFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_PictureOfTheDay)
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun recycler_checkScroll() {
        onView(withId(R.id.recycler)).perform(
            RecyclerViewActions.scrollTo<RecyclerAdapter.HeaderItemViewHolder>(
                hasDescendant(withText("Header4"))
            )
        )
    }

    @Test
    fun recycler_checkAddItem() {
        val action =
            RecyclerViewActions.actionOnItemAtPosition<RecyclerAdapter.AnotherItemViewHolder>(
                2,
                tapOnChildWithId(R.id.add)
            )
        onView(withId(R.id.recycler)).perform(action)
        onView(withId(R.id.recycler)).perform(
            RecyclerViewActions.scrollTo<RecyclerAdapter.HeaderItemViewHolder>(
                hasDescendant(withText("Generated"))
            )
        )
    }

    private fun tapOnChildWithId(id: Int) = object: ViewAction {
        override fun getConstraints(): Matcher<View>? {
            return null
        }

        override fun getDescription(): String {
            return "Нажимаем на view с указанным id"
        }

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById(id) as View
            v.performClick()
        }
    }
}