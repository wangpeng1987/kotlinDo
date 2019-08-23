package com.boo.ketlint

import android.app.Application
import com.ljb.mvp.kotlin.common.Constant.GITHUB_CLIENT_ID
import com.ljb.mvp.kotlin.common.Constant.GITHUB_CLIENT_SECRET
import com.ljb.mvp.kotlin.common.Constant.HTTP_API_DOMAIN
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import net.ljb.kt.HttpConfig
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

        //初始化网络库
        initNet()
    }

    private fun initNet() {
        val paramMap = mapOf(
            "client_id" to GITHUB_CLIENT_ID,
            "client_secret" to GITHUB_CLIENT_SECRET
        )
        HttpConfig.init(HTTP_API_DOMAIN, paramMap, paramMap, BuildConfig.DEBUG)
    }


}