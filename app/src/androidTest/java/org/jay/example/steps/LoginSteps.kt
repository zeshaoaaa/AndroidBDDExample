package org.jay.example.steps

import android.util.Log
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
import org.jay.example.R
import org.jay.example.data.BaseResponse
import org.jay.example.data.RESPONSE_CODE_SUCCESS
import org.jay.example.login.LoginActivity
import org.jay.example.login.LoginContract
import org.jay.example.utils.click
import org.jay.example.utils.typeText
import org.jay.example.utils.withToast
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * 场景名称：登陆成功
 */
const val SCENARIO_NAME_LOGIN_SUCCESSFUL = "登录成功"

/**
 * 登录特性的步骤定义
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
        rule.launchActivity(null)
    }

    @When("用户输入了手机号 (\\S+)$")
    fun `用户输入了手机号`(phone: String) {
        R.id.edPhone.typeText(phone)
    }

    @And("用户输入了密码 (\\S+)$")
    fun `用户输入了密码`(password: String) {
        R.id.edPassword.typeText(password)
    }

    @And("用户点击了登录按钮")
    fun `用户点击了登录按钮`() {
        R.id.btnLogin.click()
    }

    @Then("用户能看到“登录成功”的提示")
    fun `用户能看到“登录成功”的提示`() {
        "登录成功".withToast(rule)
    }

    @Then("用户能看到“手机号有误，请重新确认”的提示")
    fun `用户能看到“手机号有误，请重新确认”的提示`() {
        "手机号有误，请重新确认".withToast(rule)
    }

    /**
     * 特性测试完成回调
     */
    @After
    fun tearDown() {
        Log.e("hooks", "after")
    }

}