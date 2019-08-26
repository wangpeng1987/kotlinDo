package com.boo.ketlint.ui.view.act

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.boo.ketlint.R
import com.boo.ketlint.net2.Constant
import com.boo.ketlint.ui.contract.WelComeContract
import com.boo.ketlint.ui.presenter.WelComePresenter
import com.boo.ketlint.util.PermissionUtils
import com.boo.ketlint.widget.dialog.LoadingDialog
import mvp.ljb.kt.act.BaseMvpActivity

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class WelComeActivity : BaseMvpActivity<WelComeContract.IPresenter>(), WelComeContract.IView {

    private val mLoadingDialog by lazy { LoadingDialog(this) }

    override fun getLayoutId() = R.layout.activity_welcome

    override fun registerPresenter() = WelComePresenter::class.java

    override fun init(savedInstanceState: Bundle?) {
        initPermission()
    }

    private fun initPermission() {
        PermissionUtils.requestPermission(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
            ),
            Constant.PermissionCode.CODE_INIT
        )
        { _, _ ->
            //在这里处理权限结果
        }
    }

    override fun initData() {
        getPresenter().delayGoHomeTask()
    }

    override fun goHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
    override fun dismissLoadDialog() {
        mLoadingDialog.dismiss()
    }

    override fun showLoadDialog() {
        mLoadingDialog.show()
    }

}
