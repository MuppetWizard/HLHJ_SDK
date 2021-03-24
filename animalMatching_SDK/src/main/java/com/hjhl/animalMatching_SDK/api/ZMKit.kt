package com.hjhl.animalMatching_SDK.api

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment

import com.hjhl.animalMatching_SDK.common.HlhjMainActivity
import com.hjhl.animalMatching_SDK.common.HlhjMainFragment

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
    fun setColor(primaryColor: String,
                 startColor: String,
                 endColor: String ) {

        if (!TextUtils.isEmpty(primaryColor)) {
            bundle.putString("mainColor",primaryColor)
        }else{
            bundle.putString("mainColor","#7EA3FA")
        }

        if (!TextUtils.isEmpty(primaryColor)) {
            bundle.putString("startColor",startColor)
        }else{
            bundle.putString("startColor","#7EA3FA")
        }

        if (!TextUtils.isEmpty(primaryColor)) {
            bundle.putString("endColor",endColor)
        }else{
            bundle.putString("endColor","#8154FF")
        }

    }

    /**
     * 使用Activity启动页面
     */
    fun actionFromActivity(context: Context) {
        HlhjMainActivity.actionStart(context, bundle)
    }

    /**
     * 使用Fragment启动页面
     */
    fun actionFromFragment():Fragment {
        val fragment = HlhjMainFragment()
        fragment.arguments = bundle
        return fragment
    }

}