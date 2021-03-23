package com.hjhl.animalMatching_SDK.common.base

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hjhl.animalMatching_SDK.common.utils.StatusUtils

/**
 * 基础activity，
 */
abstract class HlhjBaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutId()?.let {
            setContentView(it)
            setStatusBarColor()
            setStatusBar()
            init(savedInstanceState)
        }
    }


    /**
     * 设置状态栏颜色
     */
    open fun setStatusBarColor(){
        StatusUtils.setUseStatusBarColor(this,Color.parseColor("#00ffffff"))
    }

    /**
     * 设置状态栏沉浸
     */
    open fun setStatusBar() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtils.setSystemStatus(this,true,false)
    }

    /**
     * 初始化入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 获取资源id，绑定布局
     */
    abstract fun getLayoutId() : Int?

}