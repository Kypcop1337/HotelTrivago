package com.kypcop.hoteltrivago

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView

class ClientAdapter(private val mContext: Context, private val mList: List<Client>)
    : ArrayAdapter<Client>(mContext, R.layout.listview_clients, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(mContext).inflate(R.layout.listview_clients, LinearLayout(mContext))
        val fullNameTextView = view.findViewById<TextView>(R.id.full_name_tv)
        val asrEditText = view.findViewById<TextView>(R.id.asr_textView)

        val item = mList[position]
        fullNameTextView.text = "${item.fname} ${item.lname}"
        asrEditText.text = "${item.age}, ${item.sex.getString(mContext)}, ${item.room}"

        return view
    }
}