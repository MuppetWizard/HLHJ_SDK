package com.hjhl.animalMatching_SDK.common

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.hjhl.animalMatching_SDK.R
import com.hjhl.animalMatching_SDK.common.base.HlhjBaseActivity
import com.hjhl.animalMatching_SDK.common.model.getZodiac
import com.hjhl.animalMatching_SDK.common.utils.ColorUtils

class HlhjResultActivity : HlhjBaseActivity() {

    private lateinit var ivBack:ImageView
    private lateinit var rlBg:RelativeLayout
    private lateinit var ivMen: ImageView
    private lateinit var ivWomen: ImageView
    private lateinit var tvMen: TextView
    private lateinit var tvWomen: TextView
    private lateinit var tvResult: TextView

    private lateinit var men: String
    private lateinit var woMen: String
    private lateinit var result: String
    private lateinit var startColor: String
    private lateinit var endColor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAfferentData()
        dataInput()
        initTheme()
        onClick()
    }

    private fun initTheme() {
        val color1 = ColorUtils.parseColor(startColor)
        val color2 = ColorUtils.parseColor(endColor)
        val colorArray = intArrayOf(color1, color2)

        //背景渐变色
        if (colorArray.isEmpty())  return

        val gradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colorArray)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rlBg.background = gradient
        }
    }

    /**
     * 初始化传入数据
     */
    private fun initAfferentData() {
        if (intent != null && intent.hasExtra("Result")) {
            val bundle = intent.getBundleExtra("Result")
            if (bundle != null) {
                men = bundle.getString("men") ?: "鼠"
                woMen = bundle.getString("women") ?: "牛"
                result = bundle.getString("result") ?: "出现错误，请重试"
                startColor = bundle.getString("startColor") ?: ""
                endColor = bundle.getString("endColor") ?: ""
            }
        }
    }

    /**
     * 填充数据
     */
    private fun dataInput() {
        tvMen.text = men
        tvWomen.text = woMen
        ivMen.setImageResource(getZodiac(men)!!.icon)
        ivWomen.setImageResource(getZodiac(woMen)!!.icon)
        tvResult.text = result
    }

    private fun onClick() {
        ivBack.setOnClickListener {
            finish()
        }
    }

    override fun init(savedInstanceState: Bundle?) {
        ivBack = findViewById(R.id.hlhj_back)
        rlBg = findViewById(R.id.hlhj_rl_bg)
        ivMen = findViewById(R.id.hlhj_iv_men)
        ivWomen = findViewById(R.id.hlhj_iv_women)
        tvMen = findViewById(R.id.hlhj_tv_men)
        tvWomen = findViewById(R.id.hlhj_tv_women)
        tvResult = findViewById(R.id.hlhj_tv_result)
        //获取状态栏高度后将整体下移
        rlBg.setPadding(0,getStatusBarHeight(this),0,0)

    }

    /**
     * 获取状态栏高度
     */
    private fun getStatusBarHeight(context: Context) : Int {
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        val height = resources.getDimensionPixelSize(resourceId)
        return height
    }

    override fun getLayoutId() = R.layout.hlhj_activity_result

    companion object{
        fun actionStart(context: Context,strMen: String,strWomen: String, result: String, startColor: String?,endColor: String?) {
            val intent = Intent(context,HlhjResultActivity::class.java)
            val bundle = Bundle()
            bundle.apply {
                putString("men",strMen)
                putString("women",strWomen)
                putString("result",result)
                putString("startColor",startColor)
                putString("endColor",endColor)
            }
            intent.putExtra("Result",bundle)
            context.startActivity(intent)
        }
    }
}