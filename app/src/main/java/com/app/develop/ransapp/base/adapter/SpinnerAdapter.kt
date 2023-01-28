package com.app.develop.ransapp.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.app.develop.ransapp.entity.SpinnerEntity

class SpinnerAdapter(var context: Context, var listComplete: List<SpinnerEntity>) :
    BaseAdapter() {

    private var inflter: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listComplete.size
    }

    override fun getItem(i: Int): Any {
        return listComplete[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val view = inflter.inflate(android.R.layout.simple_list_item_1, null)
        val names = view.findViewById(android.R.id.text1) as TextView
        names.text = listComplete[i].name
        return view
    }

    fun setListData(listComplete: List<SpinnerEntity>) {
        this.listComplete = listComplete
        notifyDataSetChanged()
    }

}