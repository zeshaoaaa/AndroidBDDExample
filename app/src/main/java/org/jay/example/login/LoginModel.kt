package org.jay.example.login

import io.reactivex.Observable
import org.jay.example.data.BaseResponse

const val RESPONSE_CODE_SUCCESS = 200

/**
 * 登陆页的数据层
 */
class LoginModel : LoginContract.Model {

    /**
     * 请求登录
     */
    override fun requestLogin(phone: String, password: String) : Observable<BaseResponse<String>> {
        return Observable.create {
            // 别看了，没有服务器，不请求
        }
    }

}