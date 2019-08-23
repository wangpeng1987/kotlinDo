package com.boo.ketlint.ui.contract

import io.reactivex.Observable
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/22
 * @Description input description
 **/
interface TeacherContract {

    interface IView : IViewContract {
        fun onSuccess(action: Int?)
        fun onFalied(message: String?)
    }

    interface IPresenter : IPresenterContract {
        fun getTeacherCount()
    }

    interface IModel : IModelContract {
        fun getTeacherCount():Observable<Int>
    }
}
