package com.geekbrains.pictureoftheday

import android.widget.FrameLayout
import androidx.core.view.get
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.pictureoftheday.view.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import junit.framework.TestCase
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before

@RunWith(AndroidJUnit4::class)
class EspressoTest {
    lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityContainer_NotNull() {
        scenario.onActivity {
            val fragmentContainer = it.findViewById<FrameLayout>(R.id.container)
            TestCase.assertNotNull(fragmentContainer)
        }
    }


    @Test
    fun activityNavigation_NotNull() {
        scenario.onActivity {
            val navigation = it.findViewById<BottomNavigationView>(R.id.bottom_navi)
            TestCase.assertNotNull(navigation)
        }
    }

    @Test
    fun activityNavigationItems_CorrectCount() {
        scenario.onActivity {
            val navigation = it.findViewById<BottomNavigationView>(R.id.bottom_navi)
            TestCase.assertEquals(5, navigation.menu.size())
        }
    }

    @Test
    fun activityNavigation_CurrentItems() {
        scenario.onActivity {
            val navigation = it.findViewById<BottomNavigationView>(R.id.bottom_navi)
            TestCase.assertEquals("main", navigation.menu[0].title)
            TestCase.assertEquals("EPIC", navigation.menu[1].title)
            TestCase.assertEquals("List", navigation.menu[2].title)
            TestCase.assertEquals("mars", navigation.menu[3].title)
            TestCase.assertEquals("settings", navigation.menu[4].title)
        }
    }

    @Test
    fun activityTextView_HasText() {
        val assertion: ViewAssertion = matches(
            withText("Hello! You can use the buttons below to navigate")
        )
        onView(withId(R.id.hello)).check(assertion)
    }

    @Test
    fun activityTextView_IsCompletelyDisplayed() {
        onView(withId(R.id.hello)).check(matches(isCompletelyDisplayed()))
    }


}