package com.boo.ketlint

import androidx.appcompat.app.AppCompatActivity
import com.boo.ketlint.widget.dialog.LoadingDialog

open class BaseActivity : AppCompatActivity() {
    private val mLoadingDialog by lazy { LoadingDialog(this) }

    fun dismissLoadDialog() {
        mLoadingDialog.dismiss()
    }

    fun showLoadDialog() {
        mLoadingDialog.show()
    }

}
