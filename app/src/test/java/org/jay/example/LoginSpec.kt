package org.jay.example

import io.mockk.mockk
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.jay.example.login.LoginContract
import org.jay.example.login.LoginPresenter
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * 登录相关函数测试类
 */
object LoginSpec : Spek({

    /**
     * 模拟的 View
     */
    val view = mockk<LoginContract.View>(relaxed = true)

    /**
     * 模拟的 Model
     */
    val model = mockk<LoginContract.Model>()

    /**
     * Presenter 实例
     */
    val presenter = LoginPresenter(view, model)

    Feature("验证手机号") {

        // 手机号
        var phone = ""

        // 验证结果
        var result = false

        Scenario("手机号过短") {
            Given("手机号为 666 ") {
                phone = "666"
            }
            When("验证手机号是否合法") {
                result = presenter.isPhoneValid(phone)
            }
            Then("应该判定为不合法") {
                assertFalse(result)
            }
        }

        Scenario("合法手机号") {
            Given("手机号为合法手机号") {
                phone = "15200000000"
            }
            When("验证手机号是否合法") {
                result = presenter.isPhoneValid(phone)
            }
            Then("应该判定为合法") {
                assertTrue(result)
            }
        }
    }


})