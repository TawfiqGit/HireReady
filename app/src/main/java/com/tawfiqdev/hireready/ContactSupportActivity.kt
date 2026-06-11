package com.tawfiqdev.hireready

class ContactSupportActivity : BaseActivity(R.layout.activity_contact_support) {
    override fun onReady() {
        click(R.id.sendButton) { openPage(HelpCenterActivity::class.java, clearStack = true) }
    }
}
