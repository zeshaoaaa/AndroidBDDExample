package org.jay.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.jay.example.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * 登录页
 */
class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val presenter by inject<LoginContract.Presenter> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            login()
        }

    }

    /**
     * 登录
     */
    private fun login() {
        val phone = edPhone.text.toString()
        val password = edPassword.text.toString()
        presenter.onLogin(phone, password)
    }

    /**
     * 显示 Toast 提示
     */
    override fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
