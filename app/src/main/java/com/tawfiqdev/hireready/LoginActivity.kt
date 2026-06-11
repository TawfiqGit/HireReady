package com.tawfiqdev.hireready

class LoginActivity : BaseActivity(R.layout.activity_login) {
    override fun onReady() {
        click(R.id.loginButton) { openPage(DashboardActivity::class.java, clearStack = true) }
        click(R.id.registerLink) { openPage(RegisterActivity::class.java) }
        click(R.id.forgotPasswordLink) { openPage(ForgotPasswordActivity::class.java) }
    }
}
