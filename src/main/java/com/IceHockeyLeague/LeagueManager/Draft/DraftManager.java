package com.IceHockeyLeague.LeagueManager.Draft;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPick;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Scheduler.IScheduleSystem;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DraftManager implements IDraftManager {
    private final ILeagueManagerFactory leagueManagerFactory;

    public DraftManager() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    public IPlayer findBestDraftee(List<IPlayer> players) {
        if(players.size() <= 0) {
            return leagueManagerFactory.createPlayer();
        }

        IPlayer bestPlayer = players.get(0);
        IPlayerStats bestPlayerStats = bestPlayer.getPlayerStats();
        float bestStrength = bestPlayerStats.getStrength();

        for(int i = 1; i < players.size(); i++) {
            IPlayer player = players.get(i);
            IPlayerStats stats = player.getPlayerStats();
            float strength = stats.getStrength();

            if(strength > bestStrength) {
                bestPlayer = player;
                bestStrength = strength;
            }
        }
        return bestPlayer;
    }

    public void performDraftSelectionForTeam(ITeam teamPickingDraftee, List<IPlayer> draftees) {
        List<ITeamPlayer> teamPlayers = teamPickingDraftee.getPlayers();
        IPlayer bestPlayer = this.findBestDraftee(draftees);
        draftees.remove(bestPlayer);

        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.generateTeamPlayer(bestPlayer);
        teamPlayers.add(teamPlayer);
    }

    public List<ITeam> generateTeamOrderForDraftSelection(ILeague league) {
        List<ITeam> teamsForDraftRounds = new ArrayList<>();
        IStandingSystem standingSystem = league.getStandingSystem();
        List<IStanding> regularSeasonStandings = standingSystem.getRegularSeasonStandingsInReverse();
        int regularSeasonEliminatedTeamsCount = (regularSeasonStandings.size() / 2);

        IScheduleSystem scheduleSystem = league.getScheduleSystem();
        List<IStanding> playOffSeasonStandings = standingSystem.getPlayOffSeasonStandingsInReverse(scheduleSystem.getPlayoffSchedule());

        for(int i = 0; i < regularSeasonEliminatedTeamsCount; i++) {
            IStanding standing = regularSeasonStandings.get(i);
            teamsForDraftRounds.add(standing.getTeam());
        }

        for(IStanding standing : playOffSeasonStandings) {
            teamsForDraftRounds.add(standing.getTeam());
        }
        return teamsForDraftRounds;
    }

    public List<ITeam> generateTeamOrderForDraftSelection(List<ITeam> teams, List<IDraftPick> draftPicks, int roundNumber) {
        List<IDraftPick> currentRoundDraftPicks = draftPicks.stream()
                .filter(draftPick -> draftPick.getDraftPickRoundNumber() == roundNumber).collect(Collectors.toList());

        List<ITeam> teamsForCurrentRound = new ArrayList<>(teams);

        for(IDraftPick draftPick: currentRoundDraftPicks) {
            ITeam teamTradedAwayTheirPick = draftPick.getSendingTeam();
            ITeam teamDoingAdditionalPick = draftPick.getReceivingTeam();

            int indexOfTeamToRemove = teamsForCurrentRound.indexOf(teamTradedAwayTheirPick);
            teamsForCurrentRound.remove(indexOfTeamToRemove);
            teamsForCurrentRound.add(indexOfTeamToRemove, teamDoingAdditionalPick);
        }

        return teamsForCurrentRound;
    }

}
