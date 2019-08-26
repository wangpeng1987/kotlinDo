package com.boo.ketlint.ui.view.act

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.boo.ketlint.R
import com.boo.ketlint.adapter.MainTabAdapter
import com.boo.ketlint.net2.domain.TabBean
import com.boo.ketlint.ui.contract.HomeContract
import com.boo.ketlint.ui.presenter.HomePresenter
import com.boo.ketlint.ui.view.fragment.MeFragment
import com.boo.ketlint.ui.view.fragment.SearchFragment
import com.boo.ketlint.ui.view.fragment.TodayFragment
import kotlinx.android.synthetic.main.activity_home.*
import mvp.ljb.kt.act.BaseMvpFragmentActivity

/*
 * @author wop
 * @date 2019-08-26 11:50
 * @des     主页
 */
class HomeActivity : BaseMvpFragmentActivity<HomeContract.IPresenter>(), HomeContract.IView {

    companion object {
        private const val KEY_INDEX = "index"
    }

    private var mFirstDownBack: Long = 0L
    private var mCurIndex: Int = 0

    private val mFragments = listOf<Fragment>(
        TodayFragment(),
        SearchFragment(),
        MeFragment()
    )

    private val mTabList = listOf(
        TabBean(R.drawable.bottom_tab_repos, R.string.tab1),
        TabBean(R.drawable.bottom_tab_following, R.string.tab2),
        TabBean(R.drawable.bottom_tab_my, R.string.tab3)
    )

    override fun registerPresenter() = HomePresenter::class.java

    override fun getLayoutId() = R.layout.activity_home

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, mCurIndex)
    }

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        savedInstanceState?.let {
            supportFragmentManager.popBackStackImmediate(null, 1)
            mCurIndex = it.getInt(KEY_INDEX)
        }
    }

    override fun initView() {
        tgv_group.setOnItemClickListener { openTabFragment(it) }
        tgv_group.setAdapter(MainTabAdapter(this, mTabList))
        openTabFragment(mCurIndex)
    }

    private fun openTabFragment(position: Int) {
        tgv_group.setSelectedPosition(position)
        val ft = supportFragmentManager.beginTransaction()
        ft.hide(mFragments[mCurIndex])
        var f = supportFragmentManager.findFragmentByTag(mFragments[position].javaClass.simpleName)
        if (f == null) {
            f = mFragments[position]
            ft.add(R.id.fl_content, f, f.javaClass.simpleName)
        }
        ft.show(f).commit()
        mCurIndex = position
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - mFirstDownBack > 2000) {
            Toast.makeText(this, R.string.exit_go_out, Toast.LENGTH_SHORT).show()
            mFirstDownBack = System.currentTimeMillis()
            return
        }
        super.onBackPressed()
    }
}
