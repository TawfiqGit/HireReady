package com.tawfiqdev.hireready

class PrivacyPolicyActivity : BaseActivity(R.layout.activity_privacy_policy) {
    override fun onReady() {
        click(R.id.understoodButton) { finish() }
    }
}
