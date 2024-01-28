package com.project.cricket.view.score

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.cricket.MatchDetailsResponse
import com.project.cricket.R
import com.project.cricket.adapter.ScoreBoardBattersAdapter
import com.project.cricket.adapter.ScoreBoardBowlersAdapter

class ScoreBoardActivity : AppCompatActivity() {

    companion object {
        const val team: String = "Team"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board)

        val teamObj = intent.getSerializableExtra(team) as? MatchDetailsResponse.MatchDetails.Team

        val recyclerViewBatters: RecyclerView = findViewById(R.id.scoresRecyclerView)
        val recyclerViewBowlers: RecyclerView = findViewById(R.id.bowlingScoreBoardRecyclerView)
        val totalScore: AppCompatTextView = findViewById(R.id.totalScore)
        val teamName: AppCompatTextView = findViewById(R.id.teamName)

        val layoutManagerBatter = LinearLayoutManager(applicationContext)
        val layoutManagerBowler = LinearLayoutManager(applicationContext)
        val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        val decoration2 = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.score_board_label)

        recyclerViewBatters.layoutManager = layoutManagerBatter
        recyclerViewBatters.addItemDecoration(decoration)
        recyclerViewBowlers.layoutManager = layoutManagerBowler
        recyclerViewBowlers.addItemDecoration(decoration2)

        val battersAdapter = ScoreBoardBattersAdapter(applicationContext)
        val bowlersAdapter = ScoreBoardBowlersAdapter(applicationContext)
        recyclerViewBatters.adapter = battersAdapter
        recyclerViewBowlers.adapter = bowlersAdapter

        if (teamObj != null) {
            teamName.text = teamObj.name
            totalScore.text = getString(R.string.total_score,findTotalScore(teamObj.players).toString())
            battersAdapter.updateData(teamObj.players)
            bowlersAdapter.updateData(teamObj.bowlers)
        }

    }

    private fun findTotalScore(list: List<MatchDetailsResponse.MatchDetails.Team.Player>): Int {
        var sum = 0
        for (myObject in list) {
            sum += myObject.runs
        }
        return sum
    }

}