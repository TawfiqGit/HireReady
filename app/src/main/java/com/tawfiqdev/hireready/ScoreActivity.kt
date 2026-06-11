package com.tawfiqdev.hireready

class ScoreActivity : BaseActivity(R.layout.activity_score) {
    override fun onReady() {
        click(R.id.recommendationsButton) { openPage(PreviewCvActivity::class.java) }
    }
}
