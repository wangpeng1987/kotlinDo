package com.boo.ketlint

import android.app.Application
import com.ljb.mvp.kotlin.common.Constant.GITHUB_CLIENT_ID
import com.ljb.mvp.kotlin.common.Constant.GITHUB_CLIENT_SECRET
import com.ljb.mvp.kotlin.common.Constant.HTTP_API_DOMAIN
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
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
        initUm()
    }

    private fun initNet() {
        val paramMap = mapOf(
            "client_id" to GITHUB_CLIENT_ID,
            "client_secret" to GITHUB_CLIENT_SECRET
        )
        HttpConfig.init(HTTP_API_DOMAIN, paramMap, paramMap, BuildConfig.DEBUG)
    }

    private fun initUm() {
        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(
            this,
            getString(R.string.ymAppKey),
            "china",
            UMConfigure.DEVICE_TYPE_PHONE,
            getString(R.string.ymSecret)
        )
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);

        //获取消息推送代理示例
        val mPushAgent = PushAgent.getInstance(this)
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                LOGS.e("注册成功：deviceToken：-------->  $deviceToken")
            }

            override fun onFailure(s: String, s1: String) {
                LOGS.e("注册失败：-------->  s:$s,s1:$s1")
            }
        })
    }


}