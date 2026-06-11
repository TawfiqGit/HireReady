package com.tawfiqdev.hireready

class SettingsActivity : BaseActivity(R.layout.activity_settings) {
    override fun onReady() {
        click(R.id.passwordRow) { openPage(ForgotPasswordActivity::class.java) }
        click(R.id.notificationsRow) { openPage(NotificationsActivity::class.java) }
        click(R.id.confidentialityRow) { openPage(ConfidentialityActivity::class.java) }
        click(R.id.termsRow) { openPage(TermsActivity::class.java) }
        click(R.id.deleteAccountRow) { openPage(DeleteAccountActivity::class.java) }
    }
}
