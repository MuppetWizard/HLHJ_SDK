package com.hjhl.animalMatching_SDK.common

import android.os.Bundle
import com.hjhl.animalMatching_SDK.R
import com.hjhl.animalMatching_SDK.common.base.HlhjBaseActivity

class HlhjResultActivity : HlhjBaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onClick()
    }

    private fun onClick() {

    }

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId() = R.layout.hlhj_activity_result
}