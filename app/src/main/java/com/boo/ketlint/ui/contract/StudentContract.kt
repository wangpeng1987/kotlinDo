package com.boo.ketlint.ui.contract

import com.boo.ketlint.sql.stu.Student
import com.ljb.mvp.kotlin.domain.Follower
import com.ljb.mvp.kotlin.domain.User
import io.reactivex.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/*
 * @author wop
 * @date 2019-08-22 09:17
 * @des
 */
interface StudentContract {

    interface IView : IViewContract {
        fun onSuccess(s: Student)

        fun onFalied(msg: String)
    }

    interface IPresenter : IPresenterContract {
        fun getId(id: Int)
    }

    interface IModel : IModelContract {
        fun getId(id: Int): Observable<Student>
//        fun getFollowers(page: Int): Observable<MutableList<Follower>>

    }
}
