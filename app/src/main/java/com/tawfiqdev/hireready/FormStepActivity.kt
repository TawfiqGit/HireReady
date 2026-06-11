package com.tawfiqdev.hireready

import android.app.Activity
import androidx.annotation.LayoutRes

abstract class FormStepActivity(
    @LayoutRes layoutRes: Int,
    private val nextActivity: Class<out Activity>
) : BaseActivity(layoutRes) {
    override fun onReady() {
        click(R.id.continueButton) { openPage(nextActivity) }
        click(R.id.saveButton) { openPage(nextActivity) }
        click(R.id.cancelButton) { finish() }
    }
}
