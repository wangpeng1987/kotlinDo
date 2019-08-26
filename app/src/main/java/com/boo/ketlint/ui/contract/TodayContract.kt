package com.boo.ketlint.ui.contract

import com.boo.ketlint.net.GankNews
import com.boo.ketlint.net2.domain.Follower
import com.ljb.mvp.kotlin.contract.base.ListContract
import io.reactivex.Observable
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface TodayContract {

    interface IView : ListContract.IView<GankNews>

    interface IPresenter : ListContract.IPresenter

    interface IModel : IModelContract {
        fun getToday(page:Int): Observable<MutableList<GankNews>>
    }
}