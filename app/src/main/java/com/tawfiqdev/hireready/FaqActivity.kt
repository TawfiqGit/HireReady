package com.tawfiqdev.hireready

class FaqActivity : BaseActivity(R.layout.activity_faq) {
    override fun onReady() {
        click(R.id.contactLink) { openPage(ContactSupportActivity::class.java) }
    }
}
