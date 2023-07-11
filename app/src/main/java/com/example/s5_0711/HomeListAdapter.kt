package com.example.s5_0711

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.s5_0711.databinding.HomeListviewBinding
import org.json.JSONArray
import org.json.JSONObject

class HomeListAdapter(var context:Context,var json:JSONArray):BaseAdapter() {
    override fun getCount(): Int {
        return json.length()
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var b=HomeListviewBinding.inflate((context as Activity).layoutInflater)
        b.date.text=(json[position] as JSONObject).getString("Date")
        b.title.text=(json[position] as JSONObject).getString("Title")
        return b.root
    }
}