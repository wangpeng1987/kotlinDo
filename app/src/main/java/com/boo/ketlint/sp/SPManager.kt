package com.boo.ketlint.sp

import android.content.Context
import com.tencent.mmkv.MMKV

import com.boo.ketlint.sp.SPConstants.APP_DEVICE_ID

/**
 * @author wop
 * @title SPManager
 */
class SPManager private constructor() {

    private var mMkv: MMKV? = null

    /**
     * 获取当前资源版本号
     *
     * @return
     */
    /**
     * 设置当前资源的版本号
     *
     * @param version
     */
    var resVersion: Int
        get() = mMkv!!.decodeInt(SPConstants.RES_VERSION)
        set(version) {
            mMkv!!.encode(SPConstants.RES_VERSION, version)
        }

    /**
     * 获取设备唯一id
     *
     * @return
     */
    /**
     * 设置设备唯一id
     *
     * @param deviceId
     */
    var deviceId: String
        get() = mMkv!!.decodeString(APP_DEVICE_ID)
        set(deviceId) {
            mMkv!!.encode(APP_DEVICE_ID, deviceId)
        }

    /**
     * 在application里面初始化
     *
     * @param context
     */
    fun init(context: Context) {
        MMKV.initialize(context)
        mMkv = MMKV.mmkvWithID(SPConstants.MMKV_PREFERENCES, MMKV.MULTI_PROCESS_MODE)
    }

    /**
     * 设置订阅状态标记 true->已订阅， false->未订阅
     *
     * @param hasSub
     */
    fun setSub(hasSub: Boolean) {
        mMkv!!.encode(SPConstants.SUB_HAS_SUB, hasSub)
    }

    /**
     * 获取订阅状态标记 true->已订阅， false->未订阅
     *
     * @return
     */
    fun hasSub(): Boolean {
        return mMkv!!.decodeBool(SPConstants.SUB_HAS_SUB)
    }

    companion object {

        @Volatile
        private var singleton: SPManager? = null

        val instance: SPManager?
            get() {
                if (singleton == null) {
                    synchronized(SPManager::class.java) {
                        if (singleton == null) {
                            singleton = SPManager()
                        }
                    }
                }
                return singleton
            }
    }


}
