package com.boo.ketlint.ui.presenter

import android.annotation.SuppressLint
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.boo.ketlint.ui.contract.StudentContract
import com.boo.ketlint.ui.model.StudentModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Author wop
 * @Date 2019/08/22
 * @Description input description
 **/
class StudentPresenter : BaseMvpPresenter<StudentContract.IView, StudentContract.IModel>(), StudentContract.IPresenter {

    override fun registerModel() = StudentModel::class.java

    @SuppressLint("CheckResult")
    override fun getId(id: Int) {
        getModel().getId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getMvpView().onSuccess(it)
            }, {
                it.message?.let { it1 -> getMvpView().onFalied(it1) }
            })
    }

}
