package org.jay.example.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not

/**
 * 该文件用于存放 Espresso 相关的扩展函数
 */

/**
 * 通过字符串，检查对应的 Toast 是否正在显示
 */
fun String.withToast(rule: ActivityTestRule<*>) {
    onView(withText(this))
        .inRoot(withDecorView(not(`is`(rule.activity.window.decorView))))
        .check(matches(isDisplayed()))
}

/**
 * 通过 View 的 ID 找到对应的控件，并执行点击操作
 */
fun Int.click() {
    this.withId().perform(ViewActions.click())
}

/**
 * 通过 View 的 ID 找到对应的控件，并执行输入文本操作
 */
fun Int.typeText(content: String) {
    this.withId().perform(ViewActions.typeText(content))
}

/**
 * 通过 View 的 ID 获取 ViewInteraction 实例
 */
fun Int.withId(): ViewInteraction {
    return onView(ViewMatchers.withId(this))
}