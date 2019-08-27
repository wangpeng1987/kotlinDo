package com.boo.ketlint.widget

import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Message
import android.util.Log
import com.boo.ketlint.util.ADFilterTool
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

class NoAdWebViewClient(private val context: Context, private val webView: WebView) : WebViewClient() {
    private var isClose: Boolean = false

    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            val js = ADFilterTool.getClearAdDivJs(context)
            Log.v("adJs", js)
            webView.loadUrl(js)
        }
    }


    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
        super.onPageStarted(view, url, favicon)
        if (isClose) { //如果线程正在运行就不用重新开启一个线程了
            return
        }
        Thread(Runnable {
            isClose = true
            while (isClose) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                handler.sendEmptyMessage(0x001)
            }
        }).start()
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        isClose = false
    }
}
