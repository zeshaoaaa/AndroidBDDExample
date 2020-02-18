package org.jay.example.login

import android.util.Log
import androidx.annotation.VisibleForTesting
import org.jay.example.data.RESPONSE_CODE_SUCCESS

/**
 * 手机号码长度
 */
const val LENGTH_PHONE = 11

/**
 * 登录页的业务逻辑层
 */
class LoginPresenter(
    private val view: LoginContract.View,
    private val model: LoginContract.Model
) : LoginContract.Presenter() {

    /**
     * 登录
     *
     * @param phone    手机号
     * @param password 密码
     */
    override fun onLogin(phone: String, password: String) {
        if (!isPhoneValid(phone)) {
            view.showToast("手机号有误，请重新确认")
            return
        }

        // 这里没有使用 Observer 处理异常，请勿模仿
        model.requestLogin(phone, password).subscribe {
            view.showToast(it.message)
        }

    }

    /**
     * 判断手机号是否合法
     *
     * @param  phone 手机号
     * @return true  表示手机号合法
     */
    @VisibleForTesting
    fun isPhoneValid(phone: String): Boolean {
        return phone.length == LENGTH_PHONE
    }

}





