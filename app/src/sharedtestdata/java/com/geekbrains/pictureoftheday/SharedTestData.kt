package com.geekbrains.pictureoftheday

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

object SharedTestData {
    internal val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    internal val context = ApplicationProvider.getApplicationContext<Context>()
    internal val packageName = context.packageName
    internal val tvHelloId = "hello"
}