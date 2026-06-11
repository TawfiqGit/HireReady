package com.tawfiqdev.hireready

class RegisterActivity : BaseActivity(R.layout.activity_register) {
    override fun onReady() {
        click(R.id.registerButton) { openPage(DashboardActivity::class.java, clearStack = true) }
        click(R.id.loginLink) { openPage(LoginActivity::class.java, clearStack = true) }
    }
}
