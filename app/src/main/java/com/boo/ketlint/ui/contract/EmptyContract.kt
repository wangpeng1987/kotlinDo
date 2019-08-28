package com.boo.ketlint.ui.contract

import com.boo.ketlint.net2.domain.Category
import io.reactivex.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface EmptyContract {

    interface IView : IViewContract {
        fun showPage(data: Category, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }

    interface IPresenter : IPresenterContract {
        fun onRefresh()
        fun onLoadMore()
    }

    interface IModel : IModelContract {
        fun getToday(page:Int): Observable<Category>
    }
}