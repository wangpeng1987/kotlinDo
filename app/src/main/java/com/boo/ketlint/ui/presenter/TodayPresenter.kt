package com.boo.ketlint.ui.presenter

import com.boo.ketlint.net.Category
import com.boo.ketlint.net2.rx.RxUtils
import com.boo.ketlint.net2.rx.subscribeNet
import com.boo.ketlint.ui.contract.TodayContract
import com.boo.ketlint.ui.model.TodayModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import mvp.ljb.kt.presenter.getContextEx

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class TodayPresenter : BaseMvpPresenter<TodayContract.IView, TodayContract.IModel>(), TodayContract.IPresenter {

    private var mPage = 1

    override fun registerModel() = TodayModel::class.java

    override fun onLoadMore() {
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        getModel().getToday(page)
            .compose(RxUtils.bindToLifecycle(getMvpView()))
            .compose(RxUtils.schedulerIO2Main<Category>())
            .subscribeNet(getContextEx()) {
                onNextEx {
                    getMvpView().showPage(it.results.Android, page)
                    mPage++
                }
                onErrorEx { getMvpView().errorPage(it, page) }
            }
    }
}