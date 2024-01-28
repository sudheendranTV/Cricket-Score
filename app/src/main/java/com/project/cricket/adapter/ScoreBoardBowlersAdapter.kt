package com.project.cricket.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.cricket.MatchDetailsResponse
import com.project.cricket.R


class ScoreBoardBowlersAdapter(
        private val context: Context
    ) :
        RecyclerView.Adapter<ScoreBoardBowlersAdapter.MyViewHolder>() {
        var dataList = listOf<MatchDetailsResponse.MatchDetails.Team.Bowler>()

        // ViewHolder class to hold the views for each item
        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTxt: TextView = itemView.findViewById(R.id.name)
            val runsTxt: TextView = itemView.findViewById(R.id.runs)
            val overs: TextView = itemView.findViewById(R.id.overs)
            val wickets: TextView = itemView.findViewById(R.id.wickets)
            // val sixesTxt: TextView = itemView.findViewById(R.id.balls)
            fun bindData(item: MatchDetailsResponse.MatchDetails.Team.Bowler) {
                val result = runCatching {
                    item.let {
                        nameTxt.text = item.name
                        runsTxt.text = item.runsConceded.toString()
                        overs.text = item.overs.toString()
                        wickets.text = item.wickets.toString()
                    }
                }
            }

        }

        // onCreateViewHolder to inflate the item layout and create ViewHolder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.bowling_score_board, parent, false)
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

    fun updateData(list: List<MatchDetailsResponse.MatchDetails.Team.Bowler>) {
            dataList = list
            notifyDataSetChanged()
        }

    }
