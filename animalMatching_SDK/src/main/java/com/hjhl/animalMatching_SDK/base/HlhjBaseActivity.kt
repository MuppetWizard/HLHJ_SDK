package com.hjhl.animalMatching_SDK.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 基础activity，
 */
abstract class HlhjBaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutId()?.let {
            setContentView(it)
            setStatusBar()
            init(savedInstanceState)
        }
    }


    /**
     * 设置状态栏
     */
    open fun setStatusBar(){}

    /**
     * 初始化入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 获取资源id，绑定布局
     */
    abstract fun getLayoutId() : Int?

}