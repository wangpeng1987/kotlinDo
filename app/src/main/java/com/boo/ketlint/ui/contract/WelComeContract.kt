package com.boo.ketlint.ui.contract

import io.reactivex.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
interface WelComeContract {

    interface IView : IViewContract {
        fun showLoadDialog()
        fun dismissLoadDialog()
        fun goHome()
    }

    interface IPresenter : IPresenterContract {
        fun delayGoHomeTask()
    }

    interface IModel : IModelContract {
        fun delayGoHomeTask(): Observable<Long>
    }
}