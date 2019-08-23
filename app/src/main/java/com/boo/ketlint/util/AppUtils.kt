package com.boo.ketlint.util

import android.app.Activity
import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import com.boo.ketlint.KlApplication
import java.io.File
import java.util.*

object AppUtils {


    val ACTIVITY_ALIAS_Main = "com.boo.ketlint.MainActivity"
    val ACTIVITY_ALIAS_1 = "com.boo.ketlint.ActivityAlias1"
    val ACTIVITY_ALIAS_2 = "com.boo.ketlint.ActivityAlias2"

    //动态改变桌面图片和应用名称
    fun setIcon(activity_alias: String) {
        val ctx = KlApplication.instance()
        val pm = ctx.packageManager
        val am = ctx.getSystemService(Activity.ACTIVITY_SERVICE) as ActivityManager
        // Enable/disable activity-aliases
        pm.setComponentEnabledSetting(
            ComponentName(ctx, ACTIVITY_ALIAS_Main),
            if (ACTIVITY_ALIAS_Main == activity_alias)
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            else
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        // Enable/disable activity-aliases
        pm.setComponentEnabledSetting(
            ComponentName(ctx, ACTIVITY_ALIAS_1),
            if (ACTIVITY_ALIAS_1 == activity_alias)
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            else
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        pm.setComponentEnabledSetting(
            ComponentName(ctx, ACTIVITY_ALIAS_2),
            if (ACTIVITY_ALIAS_2 == activity_alias)
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            else
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        // Find launcher and kill it
        val i = Intent(Intent.ACTION_MAIN)
        i.addCategory(Intent.CATEGORY_HOME)
        i.addCategory(Intent.CATEGORY_DEFAULT)
        val resolves = pm.queryIntentActivities(i, 0)
        for (res in resolves) {
            if (res.activityInfo != null) {
                am.killBackgroundProcesses(res.activityInfo.packageName)
            }
        }
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    fun getPackageName(context: Context): String {
        return context.packageName
    }

    /**
     * 获取VersionName(版本名称)
     *
     * @param context
     * @return 失败时返回""
     */
    fun getVersionName(context: Context): String {
        val packageManager = getPackageManager(context)
        try {
            val packageInfo = packageManager.getPackageInfo(getPackageName(context), 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return ""
    }

    /**
     * 获取VersionCode(版本号)
     *
     * @param context
     * @return 失败时返回-1
     */
    fun getVersionCode(context: Context): Int {
        val packageManager = getPackageManager(context)
        try {
            val packageInfo = packageManager.getPackageInfo(getPackageName(context), 0)
            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return -1
    }

    /**
     * 获取所有安装的应用程序,不包含系统应用
     *
     * @param context
     * @return
     */
    fun getInstalledPackages(context: Context): List<PackageInfo> {
        val packageManager = getPackageManager(context)
        val packageInfos = packageManager.getInstalledPackages(0)
        val packageInfoList = ArrayList<PackageInfo>()
        for (i in packageInfos.indices) {
            if (packageInfos[i].applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                packageInfoList.add(packageInfos[i])
            }
        }
        return packageInfoList
    }

    /**
     * 获取应用程序的icon图标
     *
     * @param context
     * @return 当包名错误时，返回null
     */
    fun getApplicationIcon(context: Context): Drawable? {
        val packageManager = getPackageManager(context)
        try {
            val packageInfo = packageManager.getPackageInfo(getPackageName(context), 0)
            return packageInfo.applicationInfo.loadIcon(packageManager)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 启动安装应用程序
     *
     * @param activity
     * @param path     应用程序路径
     */
    fun installApk(activity: Activity, path: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.fromFile(File(path)), "application/vnd.android.package-archive")
        activity.startActivity(intent)
    }

    /**
     * 获取PackageManager对象
     *
     * @param context
     * @return
     */
    private fun getPackageManager(context: Context): PackageManager {
        return context.packageManager
    }

    /**
     * 获取手机品牌
     *
     * @param context 上下文
     * @return String
     */
    fun getMobileBrand(context: Context): String {
        try {
            // android系统版本号
            return Build.BRAND
        } catch (e: Exception) {
            return "未知"
        }

    }

    //跳转到insgram指定的人
    fun jumpInstagramUserHome(context: Context, userName: String) {
        //mInstagramId = "";  该用户的用户名
        val uri1 = Uri.parse("http://instagram.com/$userName")
        val intent1 = Intent(Intent.ACTION_VIEW, uri1)
        intent1.setPackage("com.instagram.android")
        try {
            context.startActivity(intent1)
        } catch (e: Exception) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com$userName")
                )
            )
        }

    }

    /**
     * 跳转到faceCam在instagram主页
     *
     * @param context
     */
    fun jumpInstagramFaceCamHome(context: Context) {
        //mInstagramId = "";  该用户的用户名
        val uri1 = Uri.parse("https://www.instagram.com/faceturn.app/")
        val intent1 = Intent(Intent.ACTION_VIEW, uri1)
        intent1.setPackage("com.instagram.android")
        try {
            context.startActivity(intent1)
        } catch (e: Exception) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/faceturn.app/")
                )
            )
        }

    }

    fun goPlay() {
        val appPackageName = KlApplication.instance().packageName // getPackageName() from Context or Activity object
        try {
            val launchIntent = KlApplication.instance().packageManager.getLaunchIntentForPackage("com.android.vending")
            val comp = ComponentName(
                "com.android.vending",
                "com.google.android.finsky.activities.LaunchUrlHandlerActivity"
            ) // package name and activity
            launchIntent!!.component = comp
            launchIntent.data = Uri.parse("market://details?id=$appPackageName")
            KlApplication.instance().startActivity(launchIntent)
        } catch (e: Exception) {
            KlApplication.instance().startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }

    }

}
