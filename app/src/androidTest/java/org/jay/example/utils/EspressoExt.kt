package org.jay.example.utils

import android.app.Activity
import android.app.PendingIntent.getActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not

fun withToast(activity: Activity,
              toastMessage: String) {
    onView(withText(toastMessage)).inRoot(withDecorView(not(`is`(activity.window.decorView))))
        .check(matches(isDisplayed()))
}