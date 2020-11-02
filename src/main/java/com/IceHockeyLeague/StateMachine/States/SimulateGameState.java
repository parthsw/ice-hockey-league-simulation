package com.IceHockeyLeague.StateMachine.States;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrengthCalculator;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;

public class SimulateGameState extends AbstractState {

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        IConference winningTeamConference;
        IDivision winningTeamDivision;
        ITeam winningTeam;
        IConference losingTeamConference;
        IDivision losingTeamDivision;
        ITeam losingTeam;

        ISchedule schedule = league.getScheduleSystem().getScheduledMatchOnThisDate(league.getLeagueDate());
        ITeam teamA = schedule.getFirstTeam();
        ITeam teamB = schedule.getSecondTeam();
        //TODO: Check this part
//        ITeamStrengthCalculator teamStrengthCalculator = AbstractLeagueManagerFactory.getFactory().getTeamStrengthCalculator()
//        float teamAStrength = teamA.calculateTeamStrength(teamStrengthCalculator);
//        float teamBStrength = teamB.calculateTeamStrength(teamStrengthCalculator);
        float teamAStrength = teamA.getTeamStrength();
        float teamBStrength = teamB.getTeamStrength();

        if (teamAStrength > teamBStrength) {
            winningTeam = teamA;
            losingTeam = teamB;
        }
        else {
            winningTeam = teamB;
            losingTeam = teamA;
        }

        boolean flipResult = false;
        float randomUpsetValue = (float) Math.random();
        if (randomUpsetValue < league.getGamePlayConfig().getGameResolverConfig().getRandomWinChance()) {
            flipResult = true;
        }

        if (flipResult) {
            if (winningTeam == teamA) {
                winningTeam = teamB;
                losingTeam = teamA;
            }
            else {
                winningTeam = teamA;
                losingTeam = teamB;
            }
        }

        if (winningTeam == teamA) {
            winningTeamConference = schedule.getFirstConference();
            winningTeamDivision = schedule.getFirstDivision();
            losingTeamConference = schedule.getSecondConference();
            losingTeamDivision = schedule.getSecondDivision();
        }
        else {
            winningTeamConference = schedule.getSecondConference();
            winningTeamDivision = schedule.getSecondDivision();
            losingTeamConference = schedule.getFirstConference();
            losingTeamDivision = schedule.getFirstDivision();
        }

        schedule.setWinningTeam(winningTeam);
        league.getScheduleSystem().updateScheduleAfterGamePlayed(schedule);
        league.getStandingSystem().updateStatsForWinningTeam(winningTeamConference, winningTeamDivision, winningTeam);
        league.getStandingSystem().updateStatsForLosingTeam(losingTeamConference, losingTeamDivision, losingTeam);

        return AbstractStateMachineFactory.getFactory().getInjuryCheckState(teamA, teamB);
    }

    @Override
    public void welcomeMessage() {

    }
}
