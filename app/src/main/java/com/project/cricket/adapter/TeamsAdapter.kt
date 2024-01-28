package com.project.cricket.adapter

import com.project.cricket.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.cricket.MatchDetailsResponse
import com.project.cricket.listener.AdapterOnclickItemListener

class TeamsAdapter(
    private val context: Context,
    private var listener: AdapterOnclickItemListener<MatchDetailsResponse.MatchDetails.Team>
) :
    RecyclerView.Adapter<TeamsAdapter.MyViewHolder>() {
    var dataList = listOf<MatchDetailsResponse.MatchDetails.Team>()

    // ViewHolder class to hold the views for each item
   inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.teamName)
        fun bindData(item: MatchDetailsResponse.MatchDetails.Team) {
            val result = runCatching {
                item.let {
                    name.text = item.name
                }
            }
            itemView.setOnClickListener {
                listener.onClickItemListener(item)
            }

            if (result.isSuccess) {
                println("The result is ${result.getOrThrow()}")
            } else {
                println("Error occurred: ${result.exceptionOrNull()}")
            }

        }

    }

    // onCreateViewHolder to inflate the item layout and create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_layout, parent, false)
        return MyViewHolder(view)
    }

    // onBindViewHolder to bind data to the views in each item
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindData(data)
        //holder.name.text = data
    }

    // getItemCount to return the total number of items in the data set
    override fun getItemCount(): Int {
        return dataList.size
    }

    public fun updateData(list: List<MatchDetailsResponse.MatchDetails.Team>) {
        dataList = list
        notifyDataSetChanged()
    }

}
