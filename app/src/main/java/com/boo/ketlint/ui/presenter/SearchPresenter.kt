package com.boo.ketlint.ui.presenter

import com.boo.ketlint.net2.domain.SearchList
import com.boo.ketlint.net2.rx.RxUtils
import com.boo.ketlint.net2.rx.subscribeNet
import com.boo.ketlint.ui.contract.SearchContract
import com.boo.ketlint.ui.model.SearchModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import mvp.ljb.kt.presenter.getContextEx

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class SearchPresenter : BaseMvpPresenter<SearchContract.IView, SearchContract.IModel>(), SearchContract.IPresenter {

    private var mPage = 1

    override fun registerModel() = SearchModel::class.java

    override fun onLoadMore() {
        getDataFromNet(mPage)
    }

    override fun onRefresh() {
        mPage = 1
        getDataFromNet(mPage)
    }

    private fun getDataFromNet(page: Int) {
        getModel().getAllSeearch(page)
            .compose(RxUtils.bindToLifecycle(getMvpView()))
            .compose(RxUtils.schedulerIO2Main<SearchList>())
            .subscribeNet(getContextEx()) {
                onNextEx {
                    getMvpView().showPage(it, page)
                    mPage++
                }
                onErrorEx { getMvpView().errorPage(it, page) }
            }
    }
}