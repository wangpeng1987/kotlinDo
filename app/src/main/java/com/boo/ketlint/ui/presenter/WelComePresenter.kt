package com.boo.ketlint.ui.presenter

import com.boo.ketlint.net2.rx.RxUtils
import com.boo.ketlint.net2.rx.subscribeEx
import com.boo.ketlint.ui.contract.WelComeContract
import com.boo.ketlint.ui.model.WelComeModel
import io.reactivex.android.schedulers.AndroidSchedulers
import mvp.ljb.kt.presenter.BaseMvpPresenter

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class WelComePresenter : BaseMvpPresenter<WelComeContract.IView, WelComeContract.IModel>(), WelComeContract.IPresenter {

    override fun registerModel() = WelComeModel::class.java

    override fun delayGoHomeTask() {
        getModel().delayGoHomeTask()
            .compose(RxUtils.bindToLifecycle(getMvpView()))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeEx {
                onNextEx { getMvpView().goHome() }
            }
    }
}
