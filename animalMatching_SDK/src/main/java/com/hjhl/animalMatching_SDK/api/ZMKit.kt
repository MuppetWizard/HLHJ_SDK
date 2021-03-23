package com.hjhl.animalMatching_SDK.api

import android.content.Context
import android.text.TextUtils
import com.hjhl.animalMatching_SDK.business.ZMColorManager
import com.hjhl.animalMatching_SDK.business.ZMKeyManager
import com.hjhl.animalMatching_SDK.common.HlhjMainActivity

/**
 * des: 外部调用
 *
 * @author muppet
 * @date 2021/3/23
 */
object ZMKit {

    /**
     * 初始化SDk，传入上下文和请求key
     */
    fun initSDK( key: String) {
        ZMKeyManager.setData(key)
    }

    /**
     * 启动页面
     */
    fun actionSDK(context: Context) {
        HlhjMainActivity.actionStart(context)
    }

    /**
     * 设置主题色、渐变色等
     */
    fun setColor(primaryColor: String = "#7EA3FA",
                 startColor: String ="#7EA3FA",
                 endColor: String ="#8154FF" ) {
        ZMColorManager.setData(primaryColor,startColor,endColor)
    }

}