package org.jay.example

import android.app.Application
import android.util.Log
import org.jay.example.login.LoginContract
import org.jay.example.data.LoginModel
import org.jay.example.login.LoginPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    // 登录模块
    private val loginModule = module {
        // 业务逻辑层
        factory<LoginContract.Presenter> { (view: LoginContract.View) ->
            LoginPresenter(view, get())
        }

        // 数据层
        factory<LoginContract.Model> {
            LoginModel()
        }

    }

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    /**
     * 启动 Koin 容器
     */
    private fun startKoin() {
        Log.e("MyApplication", "startKoin")
        // 启动 Koin
        startKoin {
            androidContext(this@MyApplication)
            modules(loginModule)
        }

    }

}