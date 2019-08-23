package com.boo.ketlint.ui.presenter

import android.annotation.SuppressLint
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.boo.ketlint.ui.contract.TeacherContract
import com.boo.ketlint.ui.model.TeacherModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/22
 * @Description input description
 **/
class TeacherPresenter : BaseMvpPresenter<TeacherContract.IView, TeacherContract.IModel>(), TeacherContract.IPresenter {

    override fun registerModel() = TeacherModel::class.java

    @SuppressLint("CheckResult")
    override fun getTeacherCount() {
        getModel().getTeacherCount()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getMvpView().onSuccess(it)
            }, {
                getMvpView().onFalied(it.message)
            })
    }
}
