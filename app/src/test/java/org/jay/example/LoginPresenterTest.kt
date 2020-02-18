package org.jay.example

import android.util.Log
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.jay.example.data.BaseResponse
import org.jay.example.login.LoginContract
import org.jay.example.login.LoginPresenter
import org.junit.Before
import org.junit.Test
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import org.spekframework.spek2.style.gherkin.ScenarioBody

/**
 * 登录 Presenter 测试类
 */
class LoginPresenterTest {

    /**
     * 模拟的 View
     */
    private var view = mockk<LoginContract.View>(relaxed = true)

    /**
     * 模拟的 Model
     */
    private var model = mockk<LoginContract.Model>()

    /**
     * Presenter 实例
     */
    private var presenter = LoginPresenter(view, model)

    /**
     * 初始化方法，每个测试执行前都会运行一次
     */
    @Before
    fun setUp() {
        mockRequestLogin()
    }

    /**
     * 模拟登录请求
     */
    private fun mockRequestLogin() {
        every {
            model.requestLogin(any(), any())
        } returns Observable.create {
            val result = BaseResponse<String>()
            result.message = "登录成功"
            it.onNext(result)
        }
    }

    /**
     * 测试合法账户信息登录
     */
    @Test
    fun testOnLoginSuccess() {
        val phone = "15212345678"
        val password = "123456"
        testOnLogin(phone, password, "登录成功")
    }

    /**
     * 测试非法手机号登录
     */
    @Test
    fun testOnLoginFailedByIllegalPhone() {
        val phone = "666"
        val password = "000000"
        testOnLogin(phone, password, "手机号有误，请重新确认")
    }

    /**
     * 测试 onLogin 方法的逻辑
     */
    private fun testOnLogin(phone: String, password: String, message: String) {
        presenter.onLogin(phone, password)
        verify {
            view.showToast(message)
        }
    }

    /**
     * 测试手机号验证方法
     */
    @Test
    fun testIsPhoneValid() {
        var phone = "666"
        var result = presenter.isPhoneValid(phone)
        assert(!result) {
            "断言失败，输入手机号码为：666，结果应为 false"
        }

        phone = "15200000000"
        result = presenter.isPhoneValid(phone)
        assert(result) {
            "断言失败，输入的手机号码为：15200000000，结果应该为 true"
        }

    }


}