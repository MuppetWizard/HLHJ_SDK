package com.hjhl.animalMatching_SDK.common.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import com.hjhl.animalMatching_SDK.R
import com.hjhl.animalMatching_SDK.common.adapter.GVAdapter
import com.hjhl.animalMatching_SDK.common.model.Zodiac
import com.hjhl.animalMatching_SDK.common.model.getZodiac

class DialogSwitchZodiac(context: Context, themeResId: Int,tag: Int) : Dialog(context, themeResId) {

    private val mList:List<String> = listOf("鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪")
    private var dataList: MutableList<Zodiac>? = null
    private lateinit var dialogListener: DialogListener
    private var mTag = -1
    init {
        mTag = tag
    }

    fun setDialogListener(listener: DialogListener) {
        this.dialogListener = listener
    }

    interface DialogListener {
        fun getItem(name: String,tag: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        buildDialog()
    }

    private fun initData() {
        dataList = ArrayList()
        for (name in mList) {
            val zodiac = Zodiac(name, getZodiac(name)!!.icon)
            dataList?.add(zodiac)
        }
    }

    private fun buildDialog() {
        val inflater = LayoutInflater.from(context).inflate(R.layout.hlhj_dialog_switch_zodiac,null)
        val tvCancel = inflater.findViewById(R.id.hlhj_tv_cancel) as TextView
        val gvList = inflater.findViewById(R.id.hlhj_gv_list) as GridView
        gvList.adapter = GVAdapter(context,dataList)
        setContentView(inflater)
        val dialogWindow: Window? = this.window
        dialogWindow?.setGravity(Gravity.BOTTOM)
        val lp = dialogWindow?.attributes
        lp?.width = WindowManager.LayoutParams.MATCH_PARENT
        dialogWindow?.attributes = lp
        gvList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            dialogListener.getItem(mList[position],mTag)
            dismiss()
        }
        tvCancel.setOnClickListener {
            dismiss()
        }
    }


}