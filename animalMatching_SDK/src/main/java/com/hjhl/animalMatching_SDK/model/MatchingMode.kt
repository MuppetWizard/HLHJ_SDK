package com.hjhl.animalMatching_SDK.model

/**
 * 配对mode
 * {
    "code": 1,
    "msg": "success",
    "data": {
        "men": "虎",
        "women": "龙",
        "data": "美满的婚姻，你和她都有坚强的性格，结为连理，能并肩共进，特别是她对你的事业是很有帮助的。"
        }
    }
 */
data class MatchingMode(
    val code: Int,
    val data: Data,
    val msg: String
) {
    data class Data(
        val data: String,
        val men: String,
        val women: String
    )
}