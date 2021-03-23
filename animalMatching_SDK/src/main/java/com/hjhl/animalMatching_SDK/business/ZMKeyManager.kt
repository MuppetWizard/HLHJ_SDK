package com.hjhl.animalMatching_SDK.business

import android.content.Context

/**
 * des:
 *
 * @author muppet
 * @date 2021/3/23
 */
object ZMKeyManager {

    private  var iKey: IKey? = null

    fun setKeyInterface(listener: IKey) {
        this.iKey = listener
    }

    fun setData( key: String) {
        iKey?.apply {
            getKey(key)
        }
    }
}