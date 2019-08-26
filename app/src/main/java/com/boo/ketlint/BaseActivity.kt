package com.boo.ketlint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boo.ketlint.widget.dialog.LoadingDialog

open class BaseActivity : AppCompatActivity() {
    private val mLoadingDialog by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        PushAgent.getInstance(this).onAppStart();
    }

    fun dismissLoadDialog() {
        mLoadingDialog.dismiss()
    }

    fun showLoadDialog() {
        mLoadingDialog.show()
    }

}
