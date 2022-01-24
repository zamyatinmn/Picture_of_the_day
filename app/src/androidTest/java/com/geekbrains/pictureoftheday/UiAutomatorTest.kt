package com.geekbrains.pictureoftheday

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class InitialTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val packageName = context.packageName

    private val uiDevice = UiDevice.getInstance(getInstrumentation())

    @Test
    fun test_DeviceNotNull() {
        Assert.assertNotNull(uiDevice)
    }

    @Test
    fun test_AppPackageNotNull() {
        Assert.assertNotNull(packageName)
    }

    @Test
    fun test_MainActivityIntentNotNull() {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        Assert.assertNotNull(intent)
    }
}

@RunWith(AndroidJUnit4::class)
class BehaviourTest{
    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val packageName = context.packageName

    private val uiDevice = UiDevice.getInstance(getInstrumentation())

    @Before
    fun setup() {
        uiDevice.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_MainActivityIsStarted() {
        val editText = uiDevice.findObject(By.res(packageName, "hello"))
        Assert.assertNotNull(editText)
    }

    @Test
    fun test_IsCorrectText() {
        val editText = uiDevice.findObject(By.res(packageName, "hello"))
        Assert.assertEquals(editText.text, "Hello! You can use the buttons below to navigate")
    }

    companion object {
        private const val TIMEOUT = 5000L
    }
}

@RunWith(AndroidJUnit4::class)
class OpenOtherAppTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    @Test
    fun test_OpenWeatherApp() {
        uiDevice.pressHome()
        uiDevice.pressHome()
        uiDevice.findObject(UiSelector().descriptionContains("Windy")).clickAndWaitForNewWindow()

        val validation = uiDevice.findObject(UiSelector().packageName("com.windyty.android"))
        Assert.assertTrue(validation.exists())
    }
}