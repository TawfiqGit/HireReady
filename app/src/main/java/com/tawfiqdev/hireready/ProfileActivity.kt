package com.tawfiqdev.hireready

class ProfileActivity : BaseActivity(R.layout.activity_profile) {
    override fun onReady() {
        click(R.id.personalInfoRow) { openPage(PersonalInfoActivity::class.java) }
        click(R.id.myCvsRow) { openPage(MyCvsActivity::class.java) }
        click(R.id.settingsRow) { openPage(SettingsActivity::class.java) }
        click(R.id.helpRow) { openPage(HelpCenterActivity::class.java) }
        click(R.id.logoutButton) { openPage(LoginActivity::class.java, clearStack = true) }
    }
}
