package com.project.cricket.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.cricket.MatchDetailsResponse
import com.project.cricket.R

class ScoreBoardBattersAdapter(private val context: Context) :
    RecyclerView.Adapter<ScoreBoardBattersAdapter.MyViewHolder>() {
    var dataList = listOf<MatchDetailsResponse.MatchDetails.Team.Player>()

    // ViewHolder class to hold the views for each item
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTxt: TextView = itemView.findViewById(R.id.name)
        val runsTxt: TextView = itemView.findViewById(R.id.runs)
        val ballsTxt: TextView = itemView.findViewById(R.id.balls)
        val foursTxt: TextView = itemView.findViewById(R.id.balls)
        val sixesTxt: TextView = itemView.findViewById(R.id.balls)
        fun bindData(item: MatchDetailsResponse.MatchDetails.Team.Player) {
            val result = runCatching {
                item.let {
                    nameTxt.text = item.name
                    runsTxt.text = item.runs.toString()
                    ballsTxt.text = item.balls.toString()
                    foursTxt.text = item.fours.toString()
                    sixesTxt.text = item.sixes.toString()
                }
            }
        }

    }

    // onCreateViewHolder to inflate the item layout and create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.batting_scoreboard_layout, parent, false)
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

    public fun updateData(list: List<MatchDetailsResponse.MatchDetails.Team.Player>) {
        dataList = list
        notifyDataSetChanged()
    }

}