package com.tawfiqdev.hireready

class HelpCenterActivity : BaseActivity(R.layout.activity_help_center) {
    override fun onReady() {
        click(R.id.faqRow) { openPage(FaqActivity::class.java) }
        click(R.id.contactRow) { openPage(ContactSupportActivity::class.java) }
    }
}
