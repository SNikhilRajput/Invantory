package com.evs.phanidemo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evs.phanidemo.R
import com.evs.phanidemo.model.InvantoryModel
import kotlinx.android.synthetic.main.invantory_item.view.*

class InvantoryAdapter(context: Context,lists:List<InvantoryModel>,itemClick:ItemClick<InvantoryModel>) : RecyclerView.Adapter<InvantoryAdapter.ItemViewHolder>() {
    val context=context
    val lists=lists
    var selectedItem=-1
    val itemClick=itemClick

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_title=itemView.tv_title
        var tv_subtitle=itemView.tv_subtitle
        var qui=itemView.qui
        var main_layout=itemView.main_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.invantory_item,parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.tv_title.text=lists.get(position).name
        holder.tv_subtitle.text=lists.get(position).manufacture +" "+lists.get(position).qun+""+lists.get(position).unit
        holder.qui.text=lists.get(position).qun


        holder.main_layout.setOnClickListener(View.OnClickListener {
            itemClick.onItemClick(lists.get(position))
            selectedItem=position
            notifyDataSetChanged()
        })

        if(selectedItem==position)
            holder.main_layout.setBackgroundResource(R.drawable.button_shape_red)
        else holder.main_layout.setBackgroundResource(R.drawable.et_bg)
    }

    override fun getItemCount(): Int {
       return lists.size
    }
}


