package com.example.ceshi1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//创建一个适配器StarAdapter,将Activity的实例，ListView子项布局的id和数据源传递进来
class StarAdapter(val starList: List<Star>):
    RecyclerView.Adapter<StarAdapter.ViewHolder>()
{

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val starImage: ImageView = view.findViewById(R.id.starImage)
        val starName: TextView = view.findViewById(R.id.starName)       //这里我们用findViewById方法获取到ImageView和TextView的实例
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.star_item,parent,false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val star = starList[position]           //获取当前Star实例
        holder.starImage.setImageResource(star.imageId)
        holder.starName.text = star.name
    }

    override fun getItemCount() = starList.size
}