package org.jay.example.login

import io.reactivex.Observable
import org.jay.example.data.BaseResponse

class LoginContract {

    interface View {

        fun showToast(message: String?)

    }

    interface Model {

        fun requestLogin(phone: String, password: String): Observable<BaseResponse<String>>

    }

    abstract class Presenter {

        abstract fun onLogin(phone: String, password: String)
    }

}