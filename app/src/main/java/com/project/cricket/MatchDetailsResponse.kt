package com.project.cricket


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

data class MatchDetailsResponse(
    var matchDetails: MatchDetails
) {
    data class MatchDetails(
        var fallOfWickets: List<FallOfWicket?>? = null,
        var format: String? = null,
        var teams: List<Team>,
        var toss: String? = null,
        var tossDecision: String? = null
    ) {
        data class FallOfWicket(
            var team: String? = null,
            var wickets: List<Wicket?>? = null
        ) {
            data class Wicket(
                var dismissal: Dismissal? = null,
                var overs: Double? = null,
                var player: String? = null,
                var score: Int? = null
            ) {
                data class Dismissal(
                    var bowler: String? = null,
                    var fielder: String? = null,
                    var type: String? = null
                )
            }
        }

        data class Team(
            var bowlers: List<Bowler>,
            var name: String? = null,
            var players: List<Player>
        ): Serializable {
            data class Bowler(
                var name: String? = null,
                var overs: Int,
                var runsConceded: Int? = null,
                var wickets: Int
            ): Serializable

            data class Player(
                var balls: Int? = null,
                var dismissal: Dismissal? = null,
                var fours: Int? = null,
                var name: String? = null,
                var runs: Int,
                var sixes: Int? = null
            ): Serializable {
                data class Dismissal(
                    var bowler: String? = null,
                    var fielder: String? = null,
                    var type: String? = null
                ): Serializable
            }
        }
    }
}