package com.hjhl.animalMatching_SDK.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.hjhl.animalMatching_SDK.R
import com.hjhl.animalMatching_SDK.common.model.Zodiac

/**
 * des:gridiew 列表适配器
 *
 * @author muppet
 * @date 2021/3/23
 */
class GVAdapter (context: Context, list: List<Zodiac>?) : BaseAdapter(){
    private val mlist = list
    private val mContext = context

    override fun getCount() : Int{
        if (mlist?.isNotEmpty() == true) {
            return mlist.size
        }else{
            return -1
        }
    }

    override fun getItem(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder:ViewHolder? = null
        var view: View? = null
        if (convertView == null) {
            holder = ViewHolder()
            view = LayoutInflater.from(mContext).inflate(R.layout.hlhj_dialog_item,parent,false)
            holder.mImageVIew = view.findViewById(R.id.hlhj_iv_zodiac)
            holder.mTextView = view.findViewById(R.id.hlhj_tv_name)
            view.tag = holder
        }else{
            view  = convertView
            holder = convertView.tag as ViewHolder
        }

        holder.mTextView.text = mlist?.get(position)?.name
        mlist?.get(position)?.icon?.let { holder.mImageVIew.setImageResource(it) }

        return view!!
    }

    private class ViewHolder {
        lateinit var mImageVIew : ImageView
        lateinit var mTextView: TextView
    }

}