package com.hjhl.animalMatching_SDK.common

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.hjhl.animalMatching_SDK.R
import com.hjhl.animalMatching_SDK.business.IKey
import com.hjhl.animalMatching_SDK.business.IZM
import com.hjhl.animalMatching_SDK.business.ZMColorManager
import com.hjhl.animalMatching_SDK.business.ZMKeyManager
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

    private lateinit var mContext: Context
    private lateinit var mKey: String
    private var mainColor: String? = null

    private var colorList: IntArray? = null
    private var dialog: DialogSwitchZodiac? = null
    private var colorManager: ZMColorManager? = null
    private var keyManager: ZMKeyManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTheme()
        initKey()
        onClick()
    }

    private fun initTheme() {
        colorManager?.setInterface(object : IZM {
            override fun setPrimaryColor(color: String) {
                mainColor = color
            }

            override fun setGradientColor(startColor: String, endColor: String) {
                colorList?.set(0, Color.parseColor(startColor))
                colorList?.set(1, Color.parseColor(endColor))
            }
        })
        //背景渐变色
        if (colorList?.isNotEmpty() == true) {
            val gradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,colorList)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                rlBg.setBackgroundDrawable(gradient)
            } else{
                rlBg.background = gradient
            }
        }
        mainColor?.let {
            btnStart.setTextColor(Color.parseColor(it))
        }
    }

    private fun initKey() {
        keyManager?.setKeyInterface(object : IKey {
            override fun getKey( key: String) {
                mKey = key
            }

        })
    }

    override fun init(savedInstanceState: Bundle?) {
        tvMen = findViewById(R.id.hlhj_tv_men)
        tvWomen = findViewById(R.id.hlhj_tv_women)
        ivMen = findViewById(R.id.hlhj_iv_men)
        ivWomen = findViewById(R.id.hlhj_iv_women)
        btnStart = findViewById(R.id.hlhj_btn_start)
        rlBg = findViewById(R.id.hlhj_rl_bg)
    }

    override fun onResume() {
        super.onResume()

        dialog?.setDialogListener(object : DialogSwitchZodiac.DialogListener {
            override fun getItem(name: String, tag: Int) {
                if (tag == 1) {
                    tvMen.text = name
                    ivMen.setImageResource(getZodiac(name)!!.icon)
                }else{
                    tvWomen.text = name
                    ivWomen.setImageResource(getZodiac(name)!!.icon)
                }
            }
        })
    }

    private fun onClick() {
        tvMen.setOnClickListener {
            dialog = DialogSwitchZodiac(this,R.style.ActionSheetDialogStyle,1)
            dialog?.show()
            dialog?.setDialogListener(object : DialogSwitchZodiac.DialogListener {
                override fun getItem(name: String, tag: Int) {
                        tvMen.text = name
                        ivMen.setImageResource(getZodiac(name)!!.icon)
                }
            })
        }
        tvWomen.setOnClickListener {
            dialog = DialogSwitchZodiac(this,R.style.ActionSheetDialogStyle,2)
            dialog?.show()
            dialog?.setDialogListener(object : DialogSwitchZodiac.DialogListener {
                override fun getItem(name: String, tag: Int) {
                        tvWomen.text = name
                        ivWomen.setImageResource(getZodiac(name)!!.icon)
                }
            })
        }
        btnStart.setOnClickListener {

        }
    }



    override fun getLayoutId() = R.layout.hlhj_activity_main

    companion object{
        fun actionStart(context: Context) {
            val intent = Intent(context,HlhjMainActivity::class.java)
           context.startActivity(intent)
        }
    }

}