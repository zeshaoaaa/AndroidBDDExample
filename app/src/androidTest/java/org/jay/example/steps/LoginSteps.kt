package org.jay.example.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.jay.example.login.LoginActivity
import org.jay.example.R

/**
 * 登录页测试的各个步骤
 */
class LoginSteps {

    private val rule = ActivityTestRule(LoginActivity::class.java)

    @Given("用户进入了登录页")
    fun `用户进入了登录页`() {
        rule.launchActivity(null)
    }

    @When("用户输入了合法的手机号 (\\S+)$")
    fun `用户输入了手机号`(phone: String) {
        onView(withId(R.id.edPhone)).perform(typeText(phone))
    }

    @And("用户输入了合法的密码 (\\S+)$")
    fun `用户输入了密码`(password: String) {
        onView(withId(R.id.edPassword)).perform(typeText(password))
    }

    @And("用户点击了登录按钮")
    fun `用户点击了登录按钮`() {
        onView(withId(R.id.btnLogin)).perform(click())
    }

    @Then("用户能看到“登录成功”的提示")
    fun `用户能看到“登录成功”的提示`() {
        onView(withText("登陆成功"))
            .inRoot(withDecorView(not(`is`(rule.activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @When("用户输入的手机号不等于 11 位 (\\S+)\$")
    fun `用户输入的手机号不等于十一位`(phone: String) {
        onView(withId(R.id.edPhone)).perform(typeText(phone))
    }

}