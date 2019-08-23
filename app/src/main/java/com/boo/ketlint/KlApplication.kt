package com.boo.ketlint

import android.app.Application
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import kotlin.properties.Delegates

class KlApplication : Application() {

    companion object {
        var instance: KlApplication by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        QMUISwipeBackActivityManager.init(this)
    }



}