package com.boo.ketlint.widget.webview

import android.graphics.Bitmap
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebView

/**
 * Author:Ljb
 * Time:2019/4/3
 * There is a lot of misery in life
 **/
open class SimpleWebActionCallBack : WebActionCallBack {

    override fun onReceivedTitle(view: WebView?, title: String?) {
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
    }

    override fun onPageFinished(view: WebView?, url: String?) {
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
    }

}
