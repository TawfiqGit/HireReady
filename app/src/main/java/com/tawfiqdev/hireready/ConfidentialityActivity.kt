package com.tawfiqdev.hireready

class ConfidentialityActivity : BaseActivity(R.layout.activity_confidentiality) {
    override fun onReady() {
        click(R.id.privacyPolicyRow) { openPage(PrivacyPolicyActivity::class.java) }
        click(R.id.deleteAccountRow) { openPage(DeleteAccountActivity::class.java) }
    }
}
