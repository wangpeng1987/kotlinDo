package com.boo.ketlint.ui.contract

import com.boo.ketlint.net2.domain.MeResult
import com.boo.ketlint.net2.domain.Result
import com.boo.ketlint.net2.domain.SearchList
import io.reactivex.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface MeContract {

    interface IView : IViewContract {
        fun showPage(data: MutableList<MeResult>)
        fun errorPage(t: Throwable)
    }

    interface IPresenter : IPresenterContract {
        fun onRefresh()
        fun onLoadMore()
    }

    interface IModel : IModelContract {
        fun getAllMe(): Observable<MutableList<MeResult>>
    }
}