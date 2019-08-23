package com.boo.ketlint.ui.view.act

import android.content.Intent
import com.boo.ketlint.R
import com.boo.ketlint.sql.stu.Student
import com.boo.ketlint.ui.contract.StudentContract
import com.boo.ketlint.ui.presenter.StudentPresenter
import kotlinx.android.synthetic.main.activity_student.*
import mvp.ljb.kt.act.BaseMvpActivity
import org.jetbrains.anko.toast

/**
 * @Author wop
 * @Date 2019/08/22
 * @Description  https://github.com/cn-ljb/mvp-kotlin
 **/
class StudentActivity : BaseMvpActivity<StudentContract.IPresenter>(), StudentContract.IView {
    override fun onSuccess(s: Student) {
        toast("Student: " + s)
    }

    override fun onFalied(msg: String) {
        toast("onFalied: " + msg)
    }

    override fun registerPresenter() = StudentPresenter::class.java

    override fun getLayoutId(): Int = R.layout.activity_student

    override fun initView() {
        super.initView()
        textView.text = "Student Activity"
        button1.setOnClickListener({
            val intent = Intent()
            intent.setClass(this@StudentActivity, TeacherActivity::class.java)
            startActivity(intent)
        })
        button2.setOnClickListener({ getPresenter().getId(10) })
    }


}
