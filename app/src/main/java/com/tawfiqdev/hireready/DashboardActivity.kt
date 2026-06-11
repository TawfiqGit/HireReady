package com.tawfiqdev.hireready

open class DashboardActivity : BaseActivity(R.layout.activity_dashboard) {
    override fun onReady() {
        click(R.id.createCvCard) { openPage(CreateCvActivity::class.java) }
        click(R.id.createCvButton) { openPage(CreateCvActivity::class.java) }
        click(R.id.myCvsRow) { openPage(MyCvsActivity::class.java) }
        click(R.id.importCvRow) { openPage(ImportCvActivity::class.java) }
        click(R.id.scoreRow) { openPage(ScoreActivity::class.java) }
        click(R.id.previewRow) { openPage(PreviewCvActivity::class.java) }
        click(R.id.helpRow) { openPage(HelpCenterActivity::class.java) }
        click(R.id.notificationsButton) { openPage(NotificationsActivity::class.java) }
    }
}
