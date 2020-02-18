package org.jay.example.data

import io.reactivex.Observable
import org.jay.example.login.LoginContract

const val RESPONSE_CODE_SUCCESS = 200

/**
 * 登录页的数据层
 */
class LoginModel : LoginContract.Model {

    /**
     * 请求登录
     */
    override fun requestLogin(phone: String, password: String)
            : Observable<BaseResponse<String>> {
        return Observable.create {
            // 服务器没做好，啥也没有
        }
    }

}