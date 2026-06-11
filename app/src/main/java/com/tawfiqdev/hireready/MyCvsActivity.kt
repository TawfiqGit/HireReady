package com.tawfiqdev.hireready

class MyCvsActivity : BaseActivity(R.layout.activity_my_cvs) {
    override fun onReady() {
        click(R.id.createCvButton) { openPage(CreateCvActivity::class.java) }
        click(R.id.cvRow1) { openPage(PreviewCvActivity::class.java) }
        click(R.id.cvRow2) { openPage(PreviewCvActivity::class.java) }
        click(R.id.cvRow3) { openPage(PreviewCvActivity::class.java) }
    }
}
