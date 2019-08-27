package com.boo.ketlint.ui.view.fragment

import android.content.Intent
import com.boo.ketlint.R
import com.boo.ketlint.net2.domain.Category
import com.boo.ketlint.ui.contract.TodayContract
import com.boo.ketlint.ui.presenter.TodayPresenter
import com.boo.ketlint.ui.view.act.WebActivity
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.fragment_today.*
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * Created by L on 2017/7/19.
 */
class MeFragment :  BaseMvpFragment<TodayContract.IPresenter>(), TodayContract.IView {
    override fun registerPresenter() = TodayPresenter::class.java

    override fun showPage(data: Category, page: Int) {
        page_layout.setPage(PageState.STATE_SUCCESS)
    }

    override fun errorPage(t: Throwable, page: Int) {
        page_layout.setPage(PageState.STATE_ERROR)
    }

    override fun getLayoutId() = R.layout.fragment_me

    override fun initView(){
        tencentBBS.setOnClickListener({
            val intent = Intent()
            WebActivity.startActivity(activity!!, "https://www.hupu.com")
        })
        github.setOnClickListener({
            val intent = Intent()
            WebActivity.startActivity(activity!!, "https://github.com")
        })
        juejin.setOnClickListener({
            val intent = Intent()
            WebActivity.startActivity(activity!!, "https://www.hupu.com")
        })
        csdn.setOnClickListener({
            val intent = Intent()
            WebActivity.startActivity(activity!!, "https://www.hupu.com")
        })
        hupu.setOnClickListener({
            val intent = Intent()
            WebActivity.startActivity(activity!!, "https://www.hupu.com")
        })
    }

}