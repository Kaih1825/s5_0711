package com.example.s5_0711

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import org.hamcrest.CoreMatchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import kotlin.math.log

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class startTesting {
    val devices=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Before
    fun setUp(){
        launch(MainActivity::class.java)
    }

    fun login(){
        "email".id().run {
            click()
            text="abc123@mail.com"
        }

        "pwd".id().run {
            click()
            text="Asdf456!"
        }
        devices.pressBack()
        "login".id().run {
            click()
        }
    }

    @Test
    fun emailLogin(){
        "email".id().run {
            click()
            text="abc123"
            onView(withId(R.id.email)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

            text="abc123@mail.com"
            onView(withId(R.id.email)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        "pwd".id().run {
            click()
            text="abc123"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

            text="Asdf456!!"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        "login".id().run {
            click()
            assertEquals("登入失敗\n點此重新登入",text)
        }

        "pwd".id().run {
            text="Asdf456!"
        }

        "login".id().run {
            click()
        }

        "openDrawer".id().click()

        "Name".txt().run {
            assertTrue("",waitForExists(100))
        }

    }

    @Test
    fun noNameLogin(){
        "noName".id().click()
        "openDrawer".id().click()

        "訪客".txt().run {
            assertTrue("",waitForExists(100))
        }
    }

    @Test
    fun reg(){
        "reg".id().click()
        "email".id().run {
            click()
            text="abc123"
            onView(withId(R.id.email)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

            text="abc123@mail.com"
            onView(withId(R.id.email)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        "pwd".id().run {
            click()
            text="abc123"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

            text="Asdf456!"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        devices.pressBack()

        "reg".id().run {
            click()
            assertEquals("註冊失敗",text)
        }
        "name".id().run {
            onView(withId(R.id.name)).check(ViewAssertions.matches(hasErrorText("此處不能為空")))
            click()
            text="Name"
        }
        devices.pressBack()
        "country".id().run {
            click()
        }

        "US".txt().click()

        "Register".txt().run {
            assertTrue(waitForExists(1000))
        }

        "country".id().run {
            click()
        }

        "臺灣".txt().click()

        "reg".id().run {
            click()
        }
        "openDrawer".id().click()

        "Name".txt().run {
            assertTrue("",waitForExists(100))
        }


    }

    @Test
    fun home(){
        login()
        "02/21/2023".txt().run {
            assertTrue(waitForExists(100))
        }
        "person".id().click()
        "reg".id().click()
        "email".id().run {
            click()
            text="abc123"
            onView(withId(R.id.email)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

            text="abc123@mail.com"
            onView(withId(R.id.email)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        "pwd".id().run {
            click()
            text="abc123"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))

            text="Asdf456!"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        devices.pressBack()

        "reg".id().run {
            click()
            assertEquals("註冊失敗",text)
        }

        "name".id().run {
            onView(withId(R.id.name)).check(ViewAssertions.matches(hasErrorText("此處不能為空")))
            click()
            text="Name"
        }
        devices.pressBack()
        "country".id().run {
            click()
        }
        "Asdf456!".txt().run {
            assertFalse(waitForExists(100))
        }

        "US".txt().click()

        "Change Info".txt().run {
            assertTrue(waitForExists(1000))
        }

        "country".id().run {
            click()
        }

        "臺灣".txt().click()

        "logout".id().click()

        "登入".txt().run {
            assertTrue(waitForExists(100))
        }
    }


    fun String.txt(): UiObject {
        return devices.findObject(UiSelector().text(this))
    }

    fun String.id(): UiObject {
        return devices.findObject(UiSelector().resourceId("${devices.currentPackageName}:id/$this"))
    }
}