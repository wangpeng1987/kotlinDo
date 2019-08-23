package com.boo.ketlint.sp


import com.boo.ketlint.KlApplication
import com.boo.ketlint.util.AppUtils

/**
 * @author wop
 * @title SPConstants
 */
object SPConstants {

    val MMKV_PREFERENCES = AppUtils.getPackageName(KlApplication.instance()) + "_MMKV"
    //资源版本号
    val RES_VERSION = "res_version"
    //设备唯一id
    val APP_DEVICE_ID = "device_id"
    //标记订阅状态
    val SUB_HAS_SUB = "has_sub"

}
