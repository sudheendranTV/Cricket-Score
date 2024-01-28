package com.project.cricket

class Util {

    companion object {
         fun getTotalScore(list: List<MatchDetailsResponse.MatchDetails.Team.Player>): Int {
            var sum = 0
            for (myObject in list) {
                sum += myObject.runs
            }
            return sum
        }
         fun getTotalWickets(list: List<MatchDetailsResponse.MatchDetails.Team.Bowler>): Int {
            var sum = 0
            for (myObject in list) {
                sum += myObject.wickets
            }
            return sum
        }
         fun getTotalOvers(list: List<MatchDetailsResponse.MatchDetails.Team.Bowler>): Int {
            var sum = 0
            for (myObject in list) {
                sum += myObject.overs
            }
            return sum
        }
    }
}