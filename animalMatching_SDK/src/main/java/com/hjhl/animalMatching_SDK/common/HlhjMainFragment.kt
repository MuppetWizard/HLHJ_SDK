package com.hjhl.animalMatching_SDK.common

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hjhl.animalMatching_SDK.R
import com.hjhl.animalMatching_SDK.common.ui.DialogSwitchZodiac
import com.hjhl.animalMatching_SDK.common.utils.ColorUtils


/**
 * A simple [Fragment] subclass.
 * Use the [HlhjMainFragment.newInstance] factory method to
 * create an instance of this fragment.
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

    private fun getStatusBarHeight(context: Context) : Int {
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        val height = resources.getDimensionPixelSize(resourceId)
        return height
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HlhjMainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}