package com.ljb.mvp.kotlin.contract.base

import com.boo.ketlint.net.Category
import com.boo.ketlint.net.Results
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract


interface ListContract {

    interface IView<T> : IViewContract {
        fun showPage(data: Category, page: Int)
        fun errorPage(t: Throwable, page: Int)
    }

    interface IPresenter : IPresenterContract {
        fun onRefresh()
        fun onLoadMore()
    }

}

