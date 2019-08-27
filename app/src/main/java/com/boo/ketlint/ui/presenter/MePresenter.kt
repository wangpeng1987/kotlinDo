package com.boo.ketlint.ui.presenter

import com.boo.ketlint.net2.domain.MeResult
import com.boo.ketlint.net2.domain.Result
import com.boo.ketlint.net2.domain.SearchList
import com.boo.ketlint.net2.rx.RxUtils
import com.boo.ketlint.net2.rx.subscribeNet
import com.boo.ketlint.ui.contract.MeContract
import com.boo.ketlint.ui.contract.SearchContract
import com.boo.ketlint.ui.model.MeModel
import com.boo.ketlint.ui.model.SearchModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import mvp.ljb.kt.presenter.getContextEx

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class MePresenter : BaseMvpPresenter<MeContract.IView, MeContract.IModel>(), MeContract.IPresenter {

    override fun registerModel() = MeModel::class.java

    override fun onLoadMore() {
        getDataFromNet()
    }

    override fun onRefresh() {
        getDataFromNet()
    }

    private fun getDataFromNet() {
        getModel().getAllMe()
            .compose(RxUtils.bindToLifecycle(getMvpView()))
            .compose(RxUtils.schedulerIO2Main<MutableList<MeResult>>())
            .subscribeNet(getContextEx()) {
                onNextEx {
                    getMvpView().showPage(it)
                }
                onErrorEx { getMvpView().errorPage(it) }
            }
    }
}