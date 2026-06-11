package com.tawfiqdev.hireready

class ForgotPasswordActivity : BaseActivity(R.layout.activity_forgot_password) {
    override fun onReady() {
        click(R.id.sendLinkButton) { openPage(LoginActivity::class.java, clearStack = true) }
        click(R.id.loginLink) { openPage(LoginActivity::class.java, clearStack = true) }
    }
}
