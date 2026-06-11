package com.tawfiqdev.hireready

class CreateCvActivity : BaseActivity(R.layout.activity_create_cv) {
    override fun onReady() {
        click(R.id.blankCvOption) { openPage(PersonalInfoActivity::class.java) }
        click(R.id.templateOption) { openPage(PreviewCvActivity::class.java) }
        click(R.id.importCvOption) { openPage(ImportCvActivity::class.java) }
        click(R.id.cancelButton) { finish() }
    }
}
