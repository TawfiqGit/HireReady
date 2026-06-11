package com.tawfiqdev.hireready

class PreviewCvActivity : BaseActivity(R.layout.activity_preview_cv) {
    override fun onReady() {
        click(R.id.editButton) { openPage(PersonalInfoActivity::class.java) }
        click(R.id.downloadButton) { openPage(ScoreActivity::class.java) }
    }
}
