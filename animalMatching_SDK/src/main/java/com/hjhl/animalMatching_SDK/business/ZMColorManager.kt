package com.hjhl.animalMatching_SDK.business

/**
 * des:
 *
 * @author muppet
 * @date 2021/3/23
 */
object ZMColorManager {


    private var izm: IZM? = null

    fun setInterface(listener: IZM) {
        this.izm = listener
    }

    fun setData(mainColor: String, startColor: String, endColor: String) {

        izm?.apply {
            setPrimaryColor(mainColor)
            setGradientColor(startColor, endColor)
        }
    }


}