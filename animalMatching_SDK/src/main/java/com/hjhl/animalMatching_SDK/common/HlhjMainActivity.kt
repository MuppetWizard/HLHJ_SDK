package com.hjhl.animalMatching_SDK.common

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.hjhl.animalMatching_SDK.R
import com.hjhl.animalMatching_SDK.common.base.Constants
import com.hjhl.animalMatching_SDK.common.base.HlhjBaseActivity
import com.hjhl.animalMatching_SDK.common.model.MatchingMode
import com.hjhl.animalMatching_SDK.common.model.getZodiac
import com.hjhl.animalMatching_SDK.common.ui.DialogSwitchZodiac
import com.hjhl.animalMatching_SDK.common.utils.ColorUtils
import okhttp3.*
import java.io.IOException
import java.lang.Exception
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit


class HlhjMainActivity : HlhjBaseActivity() {

    private lateinit var tvMen:TextView
    private lateinit var tvWomen:TextView
    private lateinit var ivMen: ImageView
    private lateinit var ivWomen: ImageView
    private lateinit var btnStart: Button
    private lateinit var rlBg: RelativeLayout
    private lateinit var rlTittle: RelativeLayout
    private lateinit var ivBack: ImageView
    private lateinit var pb:ProgressBar

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
        val color1 = ColorUtils.parseColor(startColor)
        val color2 = ColorUtils.parseColor(endColor)
        val colorArray = intArrayOf(color1, color2)

        //背景渐变色
        if (colorArray.isEmpty())  return

        val gradient = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colorArray)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rlBg.background = gradient
        }


        btnStart.setTextColor(ColorUtils.parseColor(mainColor))
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
        pb = findViewById(R.id.hlhj_pb)
        rlBg.setPadding(0, getStatusBarHeight(this), 0, 0)

    }

    private fun onClick() {
        tvMen.setOnClickListener {
            dialog = DialogSwitchZodiac(
                    this, R.style.ActionSheetDialogStyle, ColorUtils.parseColor(mainColor)
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
                    this, R.style.ActionSheetDialogStyle, ColorUtils.parseColor(mainColor)
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
            //跳转去下一个页面
            postData()
            pb.visibility = View.VISIBLE
//            gotoPage()
        }
        ivBack.setOnClickListener {
            finish()
        }
    }

    /**
     * 上传数据
     */
    private fun postData() {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(Constants.DEFAULT_TIMEOUT,TimeUnit.MILLISECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT,TimeUnit.MILLISECONDS)
            .build()

        if (mKey == null) {
            Toast.makeText(this, "key 为空，请填写您的请求Key", Toast.LENGTH_SHORT).show()
            return
        }
        val requestBody = FormBody.Builder()
                .add("sdk_key", mKey!!)
                .add("men", tvMen.text.toString())
                .add("women", tvWomen.text.toString())
                .build()
        val request = Request.Builder()
                .url(Constants.BASE_URL+"zodiacpairing/")
                .post(requestBody)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.e(Constants.TAG, "网络错误")
                runOnUiThread {
                    pb.visibility = View.GONE
                }
            }

            //响应
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val res: String? = response.body?.string()
                    val gson = Gson()
                        val MatchingMode = gson.fromJson(res, MatchingMode::class.java)
                        if (MatchingMode.msg.equals("success")) {
                            val data = MatchingMode.data
                            //成功后跳转
                            runOnUiThread {
                                gotoPage(data)
                            }
                        } else {
                            pb.visibility = View.GONE
                            Log.e(Constants.TAG, "Failed!! see more: " + res)
                        }


                }
            }

        })
    }

    private fun gotoPage(data: MatchingMode.Data) {
        HlhjResultActivity.actionStart(this,data.men,data.women,data.data,startColor,endColor)
//        HlhjResultActivity.actionStart(this,"虎","马","哈哈哈")
        pb.visibility = View.GONE

    }
    private fun gotoPage() {
//        HlhjResultActivity.actionStart(this,data.men,data.women,data.data)
        HlhjResultActivity.actionStart(this,"虎","马","哈哈哈",startColor,endColor)

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