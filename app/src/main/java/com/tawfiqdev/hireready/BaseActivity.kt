package com.tawfiqdev.hireready

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

abstract class BaseActivity(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.WHITE, Color.WHITE)
        )
        setContentView(layoutRes)
        applyInsets()
        bindCommonNavigation()
        onReady()
    }

    protected open fun onReady() = Unit

    protected fun click(@IdRes id: Int, action: () -> Unit) {
        findViewById<View>(id)?.setOnClickListener { action() }
    }

    protected fun openPage(target: Class<out Activity>, clearStack: Boolean = false) {
        val intent = Intent(this, target)
        if (clearStack) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

    private fun applyInsets() {
        val root = findViewById<View>(R.id.screenRoot) ?: window.decorView
        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(view.paddingLeft, bars.top, view.paddingRight, bars.bottom)
            insets
        }
    }

    private fun bindCommonNavigation() {
        click(R.id.backButton) { finish() }
        click(R.id.bottomHome) { openPage(DashboardActivity::class.java, clearStack = true) }
        click(R.id.bottomCvs) { openPage(MyCvsActivity::class.java, clearStack = true) }
        click(R.id.bottomImport) { openPage(ImportCvActivity::class.java, clearStack = true) }
        click(R.id.bottomProfile) { openPage(ProfileActivity::class.java, clearStack = true) }
    }
}
