package com.hjhl.animalMatching_SDK.api

import android.content.Context
import android.os.Bundle
import android.text.TextUtils

import com.hjhl.animalMatching_SDK.common.HlhjMainActivity

/**
 * des: 外部调用
 *
 * @author muppet
 * @date 2021/3/23
 */
object ZMKit {

    private var bundle: Bundle = Bundle()

    /**
     * 初始化SDk，传入上下文和请求key
     */
    fun initSDK( key: String) {
        bundle.putString("key",key)
    }

    /**
     * 设置主题色、渐变色等
     */
    fun setColor(primaryColor: String = "#7EA3FA",
                 startColor: String ="#7EA3FA",
                 endColor: String ="#8154FF" ) {
        bundle.apply {
            putString("mainColor",primaryColor)
            putString("startColor",startColor)
            putString("endColor",endColor)
        }
    }

    /**
     * 启动页面
     */
    fun actionSDK(context: Context) {
        HlhjMainActivity.actionStart(context, bundle)
    }

}