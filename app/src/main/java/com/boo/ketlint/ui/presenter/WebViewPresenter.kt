package com.boo.ketlint.ui.presenter

import com.boo.ketlint.ui.contract.WebViewContract
import com.boo.ketlint.ui.model.WebViewModel
import mvp.ljb.kt.presenter.BaseMvpPresenter

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class WebViewPresenter : BaseMvpPresenter<WebViewContract.IView, WebViewContract.IModel>(), WebViewContract.IPresenter {

    override fun registerModel() = WebViewModel::class.java

}
