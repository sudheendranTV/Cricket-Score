package com.project.cricket.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.project.cricket.MatchDetailsResponse
import com.project.cricket.R
import com.project.cricket.Util
import com.project.cricket.adapter.TeamsAdapter
import com.project.cricket.listener.AdapterOnclickItemListener
import com.project.cricket.repositories.AccessFile
import com.project.cricket.view.score.ScoreBoardActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val assetFileJson = AccessFile.getJsonFile(applicationContext,"status.json")
        val matchDetailsResponse = Gson().fromJson(assetFileJson, MatchDetailsResponse::class.java)
        val matchDetails : MatchDetailsResponse.MatchDetails? = matchDetailsResponse.matchDetails
        val team :List<MatchDetailsResponse.MatchDetails.Team> = matchDetailsResponse.matchDetails.teams

         val teamA = matchDetails?.teams?.get(0)
         val teamB = matchDetails?.teams?.get(1)

        val recyclerView: RecyclerView = findViewById(R.id.teamsRecyclerview)
        val firstTeam: AppCompatTextView = findViewById(R.id.Team1NameTxt)
        val secondTeam: AppCompatTextView = findViewById(R.id.Team2NameTxt)
        val firstTeamRunsAndWicketsTxt: AppCompatTextView = findViewById(R.id.runsAndWickets1Txt)
        val secondTeamRunsAndWicketsTxt: AppCompatTextView = findViewById(R.id.runsAndWickets2Txt)
        val firstTeamOvers: AppCompatTextView = findViewById(R.id.overs1Txt)
        val secondTeamOvers: AppCompatTextView = findViewById(R.id.overs2Txt)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager

        if(teamA != null) {
            firstTeam.text = teamA.name
            firstTeamRunsAndWicketsTxt.text = getString(
                R.string.runs_and_wickets,
                Util.getTotalScore(teamA.players.toList()).toString(),
                Util.getTotalWickets(teamA.bowlers.toList()).toString())
            firstTeamOvers.text = getString(
                R.string.total_overs,
                Util.getTotalOvers(teamA.bowlers.toList()).toString())
        }

        if(teamB != null) {
            secondTeam.text = teamB.name
            secondTeamRunsAndWicketsTxt.text = getString(
                R.string.runs_and_wickets,
                Util.getTotalScore(teamB.players.toList()).toString(),
                Util.getTotalWickets(teamB.bowlers.toList()).toString())
            secondTeamOvers.text = getString(
                R.string.total_overs,
                Util.getTotalOvers(teamB.bowlers.toList()).toString())

        }

        val teamAdapter = TeamsAdapter(applicationContext,object:
            AdapterOnclickItemListener<MatchDetailsResponse.MatchDetails.Team> {
            override fun onClickItemListener(team: MatchDetailsResponse.MatchDetails.Team) {
                val intent = Intent(applicationContext, ScoreBoardActivity::class.java)
                intent.putExtra(ScoreBoardActivity.team, team)
                startActivity(intent)
            }
        })
        recyclerView.adapter = teamAdapter
        teamAdapter.updateData(team)



    }
}