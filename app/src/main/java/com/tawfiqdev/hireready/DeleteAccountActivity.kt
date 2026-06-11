package com.tawfiqdev.hireready

class DeleteAccountActivity : BaseActivity(R.layout.activity_delete_account) {
    override fun onReady() {
        click(R.id.deleteButton) { openPage(LoginActivity::class.java, clearStack = true) }
        click(R.id.cancelButton) { finish() }
    }
}
