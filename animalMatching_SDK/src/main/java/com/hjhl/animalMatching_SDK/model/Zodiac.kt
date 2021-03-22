package com.hjhl.animalMatching_SDK.model

import com.hjhl.animalMatching_SDK.R

class Zodiac(val name: String, val icon: Int)

private val zodiac = mapOf(
    "鼠" to Zodiac("鼠", R.mipmap.hlhj_rat),
    "牛" to Zodiac("牛", R.mipmap.hlhj_ox),
    "虎" to Zodiac("虎", R.mipmap.hlhj_tiger),
    "兔" to Zodiac("兔", R.mipmap.hlhj_rabbit),
    "龙" to Zodiac("龙", R.mipmap.hlhj_dragon),
    "蛇" to Zodiac("蛇", R.mipmap.hlhj_snake),
    "马" to Zodiac("马", R.mipmap.hlhj_horse),
    "羊" to Zodiac("羊", R.mipmap.hlhj_sheep),
    "猴" to Zodiac("猴", R.mipmap.hlhj_monkey),
    "鸡" to Zodiac("鸡", R.mipmap.hlhj_rooster),
    "狗" to Zodiac("狗", R.mipmap.hlhj_dog),
    "猪" to Zodiac("猪", R.mipmap.hlhj_pig)
)

fun getZodiac(str: String) : Zodiac? {
    return zodiac[str]
}