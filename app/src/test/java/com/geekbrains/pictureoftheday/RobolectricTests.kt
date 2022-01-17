package com.geekbrains.pictureoftheday

import android.os.Build
import android.widget.FrameLayout
import androidx.core.view.get
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.pictureoftheday.view.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class RobolectricTests {

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
            assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityContainer_NotNull() {
        scenario.onActivity {
            val fragmentContainer = it.findViewById<FrameLayout>(R.id.container)
            assertNotNull(fragmentContainer)
        }
    }


    @Test
    fun activityNavigation_NotNull() {
        scenario.onActivity {
            val navigation = it.findViewById<BottomNavigationView>(R.id.bottom_navi)
            assertNotNull(navigation)
        }
    }

    @Test
    fun activityNavigationItems_CorrectCount() {
        scenario.onActivity {
            val navigation = it.findViewById<BottomNavigationView>(R.id.bottom_navi)
            assertEquals(5, navigation.menu.size())
        }
    }

    @Test
    fun activityNavigation_CurrentItems() {
        scenario.onActivity {
            val navigation = it.findViewById<BottomNavigationView>(R.id.bottom_navi)
            assertEquals("main", navigation.menu[0].title)
            assertEquals("EPIC", navigation.menu[1].title)
            assertEquals("List", navigation.menu[2].title)
            assertEquals("mars", navigation.menu[3].title)
            assertEquals("settings", navigation.menu[4].title)
        }
    }
}