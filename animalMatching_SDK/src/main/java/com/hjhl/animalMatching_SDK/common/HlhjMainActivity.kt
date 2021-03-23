package com.hjhl.animalMatching_SDK.common

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.text.TextUtils
import android.widget.*
import com.hjhl.animalMatching_SDK.R
import com.hjhl.animalMatching_SDK.common.base.HlhjBaseActivity
import com.hjhl.animalMatching_SDK.common.model.getZodiac
import com.hjhl.animalMatching_SDK.common.ui.DialogSwitchZodiac

class HlhjMainActivity : HlhjBaseActivity() {

    private lateinit var tvMen:TextView
    private lateinit var tvWomen:TextView
    private lateinit var ivMen: ImageView
    private lateinit var ivWomen: ImageView
    private lateinit var btnStart: Button
    private lateinit var rlBg: RelativeLayout
    private lateinit var rlTittle: RelativeLayout
    private lateinit var ivBack: ImageView

    private var mKey: String? = null
    private var mainColor: String? = "#7EA3FA"
    private var startColor: String? = "#7EA3FA"
    private var endColor: String? = "#8154FF"

    private var dialog: DialogSwitchZodiac? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAfferentData()
        initTheme()
        onClick()
    }

    private fun initAfferentData() {
        if (intent != null && intent.hasExtra("bundle")) {
            val bundle = intent.getBundleExtra("bundle")
            if (TextUtils.isEmpty(bundle?.getString("key"))) {

                throw IllegalArgumentException("key is empty")
            }else{

                mKey = bundle?.getString("key")
            }
            if (bundle != null) {
                mainColor = bundle.getString("mainColor") ?: "#7EA3FA"
                startColor = bundle.getString("startColor") ?: "#7EA3FA"
                endColor = bundle.getString("endColor") ?: "#8154FF"
            }
        }
    }

    private fun initTheme() {
        val color1 = Color.parseColor(startColor)
        val color2 = Color.parseColor(endColor)
        val colorArray = intArrayOf(color1, color2)

        //背景渐变色
        if (colorArray.isEmpty())  return

        val gradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colorArray)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rlBg.background = gradient
        }


        btnStart.setTextColor(Color.parseColor(mainColor))
    }

    override fun init(savedInstanceState: Bundle?) {
        tvMen = findViewById(R.id.hlhj_tv_men)
        tvWomen = findViewById(R.id.hlhj_tv_women)
        ivMen = findViewById(R.id.hlhj_iv_men)
        ivWomen = findViewById(R.id.hlhj_iv_women)
        btnStart = findViewById(R.id.hlhj_btn_start)
        rlBg = findViewById(R.id.hlhj_rl_bg)
        rlTittle = findViewById(R.id.hlhj_rl_tittle)
        ivBack = findViewById(R.id.hlhj_back)
        rlBg.setPadding(0,getStatusBarHeight(this),0,0)

    }

    private fun onClick() {
        tvMen.setOnClickListener {
            dialog = DialogSwitchZodiac(
                this, R.style.ActionSheetDialogStyle, Color.parseColor(
                    mainColor
                )
            )
            dialog?.show()
            dialog?.setDialogListener(object : DialogSwitchZodiac.DialogListener {
                override fun getItem(name: String) {
                    tvMen.text = name
                    ivMen.setImageResource(getZodiac(name)!!.icon)
                }
            })
        }
        tvWomen.setOnClickListener {
            dialog = DialogSwitchZodiac(
                this, R.style.ActionSheetDialogStyle, Color.parseColor(
                    mainColor
                )
            )
            dialog?.show()
            dialog?.setDialogListener(object : DialogSwitchZodiac.DialogListener {
                override fun getItem(name: String) {
                    tvWomen.text = name
                    ivWomen.setImageResource(getZodiac(name)!!.icon)
                }
            })
        }
        btnStart.setOnClickListener {

        }
        ivBack.setOnClickListener {
            finish()
        }
    }

    private fun getStatusBarHeight(context: Context) : Int {
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        val height = resources.getDimensionPixelSize(resourceId)
        return height
    }

    override fun getLayoutId() = R.layout.hlhj_activity_main

    companion object{
        fun actionStart(context: Context, bundle: Bundle) {
            val intent = Intent(context, HlhjMainActivity::class.java)
            intent.putExtra("bundle", bundle)
           context.startActivity(intent)
        }
    }

}