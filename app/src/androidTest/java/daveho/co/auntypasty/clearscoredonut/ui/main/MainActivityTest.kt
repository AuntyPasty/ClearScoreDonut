package daveho.co.auntypasty.clearscoredonut.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import daveho.co.auntypasty.clearscoredonut.R
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    @get:Rule var mainActivityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testButtonReturnsExpectedResult() {

        onView(withId(R.id.credit_score_value))
            .check(matches(withText("Ready")))

        onView(withId(R.id.start_button)).perform(click())

        onView(withId(R.id.credit_score_value))
            .check(matches(withText("514")))

        onView(withId(R.id.credit_score_suffix))
            .check(matches(isDisplayed()))

        onView(withId(R.id.credit_score_suffix))
            .check(matches(withText(containsString("700"))))
    }
}