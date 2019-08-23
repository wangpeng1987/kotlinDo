package com.boo.ketlint.widget.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.text.TextUtils
import android.webkit.*
import com.boo.ketlint.BuildConfig
import com.boo.ketlint.LOGS

/**
 * Author:Ljb
 * Time:2019/4/3
 * There is a lot of misery in life
 **/
class WebViewProxy(var mContext: Context, private val mWebView: WebView, private val mWebAction: WebActionCallBack?) {

    private var mUrl: String = ""

    init {
        initSetting()
        initJsCall()
        initDownListener()
        initWebChromeClient()
        initWebViewClient()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initSetting() {
        val setting = mWebView.settings
        setting.javaScriptEnabled = true
        setting.javaScriptCanOpenWindowsAutomatically = true
        setting.loadsImagesAutomatically = true
        setting.pluginState = WebSettings.PluginState.ON
        setting.cacheMode = WebSettings.LOAD_DEFAULT
        // 启用DOM存储API
        setting.domStorageEnabled = true // 允许html localStorage
        setting.databaseEnabled = true
        setting.setGeolocationEnabled(true) // 设置定位的数据库路径
        setting.useWideViewPort = true
        setting.setSupportMultipleWindows(true)
        setting.loadWithOverviewMode = true
        setting.setSupportZoom(false)
        setting.setRenderPriority(WebSettings.RenderPriority.HIGH)
        setting.setAppCacheEnabled(true)//缓存
        setting.saveFormData = true
    }

    @SuppressLint("AddJavascriptInterface")
    private fun initJsCall() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        }
        mWebView.addJavascriptInterface(JsApi(mContext), null)
    }

    private fun initDownListener() {
        mWebView.setDownloadListener { url, _, _, _, _ ->
            goOutWeb(url)
        }
    }

    private fun initWebChromeClient() {
        mWebView.webChromeClient = object : WebChromeClient() {

            override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?, result: JsPromptResult?): Boolean {
                return super.onJsPrompt(view, url, message, defaultValue, result)
            }

            override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                return super.onJsConfirm(view, url, message, result)
            }

            //配置权限（同样在WebChromeClient中实现）
            override fun onGeolocationPermissionsShowPrompt(origin: String, callback: GeolocationPermissions.Callback) {
                callback.invoke(origin, true, true)
                super.onGeolocationPermissionsShowPrompt(origin, callback)
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                LOGS.e("webview progress -> $newProgress")
                mWebAction?.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                mWebAction?.onReceivedTitle(view, title)
            }
        }
    }

    private fun initWebViewClient() {
        mWebView.webViewClient = object : WebViewClient() {

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                mWebAction?.onReceivedError(view, request, error)
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (mWebAction != null && mWebAction.shouldOverrideUrlLoading(view, url)) {
                    return true
                } else if (url.startsWith("http")) {
                    val hit = view.hitTestResult
                    if (hit != null) {
                        return false
                    }
                    loadUrl(url)
                    return true
                } else if (url.startsWith("tel:")) {
                    openCallTel(mContext, url)
                    return true
                }
                goOutWeb(url)
                return false
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view.settings.blockNetworkImage = true
                mWebAction?.onPageStarted(view, url, favicon)
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, error: SslError?) {
                handler.proceed()
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                view.settings.blockNetworkImage = false
                mWebAction?.onPageFinished(view, url)
            }

        }
    }

    fun loadUrl(url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        mUrl = url
        LOGS.e(mUrl)
        mWebView.loadUrl(mUrl)
    }


    fun callJs(js: String) {
        mWebView.loadUrl(js)
    }


    fun onWebViewPause() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.onPause()
        }
        mWebView.pauseTimers()
    }

    fun onWebViewResume() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mWebView.onResume()
        }
        mWebView.resumeTimers()
    }

    fun canGoBack(): Boolean {
        return mWebView.canGoBack()
    }

    fun goBack() {
        mWebView.goBack()
    }

    fun onDestroy() {
        try {
            mWebView.stopLoading()
            mWebView.webChromeClient = null
            mWebView.webViewClient = null
            mWebView.settings.javaScriptEnabled = false
            // 清除webview绑定的一切缓存,4.4版本以上会崩溃
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                mWebView.clearCache(true)
            }
            mWebView.removeAllViews()
            mWebView.destroy()
        } catch (t: Throwable) {
            LOGS.e(t.toString())
        }
    }

    private fun openCallTel(context: Context, phone: String) {
        var url = phone
        if (!url.startsWith("tel:")) {
            url = "tel:$url"
        }
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun goOutWeb(url: String?) {
        if (TextUtils.isEmpty(url)) return
        try {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            mContext.startActivity(intent)
        } catch (e: Exception) {
            LOGS.e(e.toString())
        }
    }
}
