package com.hjhl.animalMatching_SDK.common

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.hjhl.animalMatching_SDK.R
import com.hjhl.animalMatching_SDK.common.base.Constants
import com.hjhl.animalMatching_SDK.common.model.MatchingMode
import com.hjhl.animalMatching_SDK.common.model.getZodiac
import com.hjhl.animalMatching_SDK.common.ui.DialogSwitchZodiac
import com.hjhl.animalMatching_SDK.common.utils.ColorUtils
import com.hjhl.animalMatching_SDK.common.utils.StatusUtils
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 *
 */
class HlhjMainFragment : Fragment() {

    private lateinit var tvMen: TextView
    private lateinit var tvWomen: TextView
    private lateinit var ivMen: ImageView
    private lateinit var ivWomen: ImageView
    private lateinit var btnStart: Button
    private lateinit var rlBg: RelativeLayout
    private lateinit var rlTittle: RelativeLayout
    private lateinit var ivBack: ImageView
    private lateinit var pb: ProgressBar

    private var mKey: String? = null
    private var mainColor: String? = "#7EA3FA"
    private var startColor: String? = "#7EA3FA"
    private var endColor: String? = "#8154FF"

    private var dialog: DialogSwitchZodiac? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mKey = it.getString("key")
            mainColor = it.getString("mainColor") ?: "#7EA3FA"
            startColor = it.getString("startColor") ?: "#7EA3FA"
            endColor = it.getString("endColor") ?: "#8154FF"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.hlhj_main_fragment, container, false)
        tvMen = view.findViewById(R.id.hlhj_tv_men)
        tvWomen = view.findViewById(R.id.hlhj_tv_women)
        ivMen = view.findViewById(R.id.hlhj_iv_men)
        ivWomen = view.findViewById(R.id.hlhj_iv_women)
        btnStart = view.findViewById(R.id.hlhj_btn_start)
        rlBg = view.findViewById(R.id.hlhj_rl_bg)
        rlTittle = view.findViewById(R.id.hlhj_rl_tittle)
        ivBack = view.findViewById(R.id.hlhj_back)
        pb = view.findViewById(R.id.hlhj_pb)
        rlBg.setPadding(0, getStatusBarHeight(activity!!), 0, 0)

        initTheme()
        onClick()
        return view
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

    private fun onClick() {
        tvMen.setOnClickListener {
            dialog = DialogSwitchZodiac(
                activity!!, R.style.ActionSheetDialogStyle, ColorUtils.parseColor(mainColor)
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
                activity!!, R.style.ActionSheetDialogStyle, ColorUtils.parseColor(mainColor)
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
        }
        ivBack.setOnClickListener {

        }
    }
    /**
     * 上传数据
     */
    private fun postData() {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()

        if (mKey == null) {
            Toast.makeText(activity, "key 为空，请填写您的请求Key", Toast.LENGTH_SHORT).show()
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
                activity?.runOnUiThread {
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
                        activity?.runOnUiThread {
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
        HlhjResultActivity.actionStart(activity!!,data.men,data.women,data.data,startColor,endColor)
        pb.visibility = View.GONE

    }

    private fun getStatusBarHeight(context: Context) : Int {
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        val height = resources.getDimensionPixelSize(resourceId)
        return height
    }

}