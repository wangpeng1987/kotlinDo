@file:Suppress("DEPRECATION")

package com.boo.ketlint.ui.view.act

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.boo.ketlint.LOGS
import com.boo.ketlint.R
import com.boo.ketlint.ui.contract.WebViewContract
import com.boo.ketlint.ui.presenter.WebViewPresenter
import com.boo.ketlint.widget.NoAdWebViewClient
import com.boo.ketlint.widget.webview.SimpleWebActionCallBack
import com.boo.ketlint.widget.webview.WebViewProxy
import com.ljb.page.PageState
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.layout_common_title.*
import kotlinx.android.synthetic.main.layout_web.*
import mvp.ljb.kt.act.BaseMvpActivity
import org.jetbrains.anko.toast

class WebActivity : BaseMvpActivity<WebViewContract.IPresenter>(), WebViewContract.IView {

    companion object {
        private const val KEY_URL = "url"
        private const val KEY_TITLE = "title"

        fun startActivity(context: Context, url: String, title: String = "") {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra(KEY_URL, url)
            intent.putExtra(KEY_TITLE, title)
            context.startActivity(intent)
        }
    }

    private var mTitle: String? = null
    private var mUrl: String? = null
    private lateinit var mProxy: WebViewProxy

    override fun registerPresenter() = WebViewPresenter::class.java

    override fun getLayoutId() = R.layout.activity_web

    override fun init(savedInstanceState: Bundle?) {
        mTitle = intent.getStringExtra(KEY_TITLE)
        mUrl = intent.getStringExtra(KEY_URL)
        LOGS.e("WebActivity : " + mUrl)
        if (TextUtils.isEmpty(mUrl)) finish()
    }

    override fun initView() {
        iv_back.setOnClickListener { onBackPressed() }
        tv_copy.visibility = View.VISIBLE
        tv_copy.setOnClickListener { toCopy() }

        val webSettings = webview!!.settings
        //设置支持javaScript脚本语言
        webSettings.javaScriptEnabled = true
        webSettings.allowContentAccess = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.mediaPlaybackRequiresUserGesture = true
        webSettings.builtInZoomControls = true
        webSettings.setAppCacheEnabled(true)
        webSettings.setDomStorageEnabled(true)
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        webSettings.setGeolocationEnabled(true)
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true
        webSettings.useWideViewPort = true // 关键点
        webSettings.allowFileAccess = true // 允许访问文件
        webSettings.setSupportZoom(true) // 支持缩放
        webSettings.loadWithOverviewMode = true
        webSettings.pluginState = WebSettings.PluginState.ON
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE // 不加载缓存内容
        webview.webChromeClient = WebChromeClient()
        webview.webViewClient = NoAdWebViewClient(this, webView = webview)

        mProxy = WebViewProxy(this, webview, object : SimpleWebActionCallBack() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                if (TextUtils.isEmpty(mTitle)) tv_title.text = title
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                page_layout.setPage(PageState.STATE_LOADING)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                mUrl = url
                page_layout.setPage(PageState.STATE_SUCCESS)
            }
        })
    }

    //把搜索链接写入剪切板
    private fun toCopy() {
// 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 将文本内容放到系统剪贴板里。
        cm.text = mUrl
        LOGS.e("WebActivity 链接已写入剪切板: " + mUrl)
        toast("链接已写入剪切板！")
    }

    override fun initData() {
        if (!TextUtils.isEmpty(mTitle)) {
            tv_title.text = mTitle
        }
        if (TextUtils.isEmpty(mUrl)) return
        if (!mUrl!!.startsWith("http")) {
            mUrl = "http://$mUrl"
        }
        mProxy.loadUrl(mUrl!!)
    }

    override fun onPause() {
        super.onPause()
        mProxy.onWebViewPause()
    }

    override fun onResume() {
        super.onResume()
        mProxy.onWebViewResume()
    }

    override fun onDestroy() {
        mProxy.onDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (mProxy.canGoBack()) {
            mProxy.goBack()
        } else {
            super.onBackPressed()
        }
    }

}

