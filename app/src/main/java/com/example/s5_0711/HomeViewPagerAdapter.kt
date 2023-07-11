package com.example.s5_0711

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.s5_0711.databinding.HomeViewpagerBinding

class HomeViewPagerAdapter(var context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class viewHolder(view: View):RecyclerView.ViewHolder(view){
        var img=view.findViewById<ImageView>(R.id.img)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var b=HomeViewpagerBinding.inflate((context as Activity).layoutInflater,parent,false)
        return viewHolder(b.root)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var v=holder as viewHolder
        var imgs= arrayOf(R.drawable.n1,R.drawable.n2,R.drawable.n3)
        v.img.setImageDrawable(context.resources.getDrawable(imgs[position]))
    }
}