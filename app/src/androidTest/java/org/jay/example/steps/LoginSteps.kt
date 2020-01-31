package org.jay.example.steps

import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import cucumber.api.Scenario
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.jay.example.R
import org.jay.example.data.BaseResponse
import org.jay.example.data.RESPONSE_CODE_SUCCESS
import org.jay.example.login.LoginActivity
import org.jay.example.login.LoginContract
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

const val SCENARIO_NAME_LOGIN_SUCCESSFUL = "登录成功"
/**
 * 登录页测试的各个步骤
 */
class LoginSteps {

    /**
     * Activity 测试规则
     */
    private val rule = ActivityTestRule(LoginActivity::class.java)

    /**
     * 初始化，测试运行前会调用该函数
     */
    @Before
    fun setUp(scenario: Scenario) {
        Log.e("hooks", "before---scenario: " + scenario.name)
        // 场景登录成功，需要请求登录接口
        if (scenario.name == SCENARIO_NAME_LOGIN_SUCCESSFUL) {
            loadMockModules()
        }
    }

    /**
     * 使用模拟模块替换真实模块
     */
    private fun loadMockModules() {
        val model = mockk<LoginContract.Model>()
        val module = module {
            // 业务逻辑层
            factory(override = true) {
                model
            }
        }
        mockRequestLogin(model)
        println("hooks---loadMockModules")
        loadKoinModules(module)
    }

    /**
     * 模拟登陆请求
     */
    private fun mockRequestLogin(model: LoginContract.Model) {
        every {
            model.requestLogin(any(), any())
        } returns Observable.create {
            val result = BaseResponse<String>()
            result.code = RESPONSE_CODE_SUCCESS
            it.onNext(result)
        }
    }

    @Given("用户进入了登录页")
    fun `用户进入了登录页`() {
        println("用户进入了登录页666")
        rule.launchActivity(null)
    }

    @When("用户输入了手机号 (\\S+)$")
    fun `用户输入了手机号`(phone: String) {
        onView(withId(R.id.edPhone)).perform(typeText(phone))
    }

    @And("用户输入了密码 (\\S+)$")
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

    @Then("用户能看到“手机号有误，请重新确认”的提示")
    fun `用户能看到“手机号有误，请重新确认”的提示`() {
        onView(withText("手机号有误，请重新确认"))
            .inRoot(withDecorView(not(`is`(rule.activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * 特性测试完成回调
     */
    @After
    fun tearDown() {
        Log.e("hooks", "after")
    }

}