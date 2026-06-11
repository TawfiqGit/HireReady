package com.tawfiqdev.hireready

class TermsActivity : BaseActivity(R.layout.activity_terms) {
    override fun onReady() {
        click(R.id.understoodButton) { finish() }
    }
}
