package com.boo.ketlint.util

import android.content.Context
import android.content.res.Resources
import com.boo.ketlint.R

object ADFilterTool {
    fun getClearAdDivJs(context: Context): String {
        var js = "javascript:"
        val res = context.resources
        val adDivs = res.getStringArray(R.array.adBlockDiv)
        for (i in adDivs.indices) {
            js += "var adDiv" + i + "= document.getElementById('" + adDivs[i] + "');if(adDiv" + i + " != null)adDiv" + i + ".parentNode.removeChild(adDiv" + i + ");"
        }
        return js
    }
}
