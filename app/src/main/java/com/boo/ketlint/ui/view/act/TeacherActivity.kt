package com.boo.ketlint.ui.view.act

import com.boo.ketlint.R
import com.boo.ketlint.ui.contract.TeacherContract
import com.boo.ketlint.ui.presenter.TeacherPresenter
import kotlinx.android.synthetic.main.activity_student.*
import mvp.ljb.kt.act.BaseMvpActivity
import org.jetbrains.anko.toast

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/22
 * @Description input description
 **/
class TeacherActivity : BaseMvpActivity<TeacherContract.IPresenter>() , TeacherContract.IView {

    override fun registerPresenter() = TeacherPresenter::class.java

    override fun getLayoutId(): Int  = R.layout.activity_teacher

    override fun initData() {
        super.initData()
        getPresenter().getTeacherCount()
    }

    override fun initView() {
        super.initView()
        textView.text = "Teacher Activity"
    }

    override fun onSuccess(action: Int?) {
        toast("TeacherActivity size : " + action)
    }

    override fun onFalied(message: String?) {
        toast("TeacherActivity: " + message)
    }

}
