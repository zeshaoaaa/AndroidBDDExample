package org.jay.example.steps

import android.util.Log
import cucumber.api.java.After
import cucumber.api.java.Before
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.jay.example.data.BaseResponse
import org.jay.example.login.LoginContract
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 *
 */
class Hooks {

    @Before
    fun setUp() {
        Log.e("before", "before")
        loadMockModules()
        println("hooks---before")
    }

    /**
     * 加载模拟的模块
     */
    private fun loadMockModules () {
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
            result.message = "登陆成功"
            it.onNext(result)
        }
    }

    @After
    fun tearDown() {
        println("hooks---after")
    }

}