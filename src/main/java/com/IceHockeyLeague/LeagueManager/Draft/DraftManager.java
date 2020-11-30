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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DraftManager implements IDraftManager {
    private final ILeagueManagerFactory leagueManagerFactory;
    private final Logger LOGGER = LogManager.getLogger(DraftManager.class);

    public DraftManager() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    public IPlayer findBestDraftee(List<IPlayer> players) {
        if (players.size() <= 0) {
            LOGGER.warn("Invalid list of draftees were passed in findBestDraftee()");
            return leagueManagerFactory.createPlayer();
        }

        IPlayer bestPlayer = players.get(0);
        IPlayerStats bestPlayerStats = bestPlayer.getPlayerStats();
        float bestStrength = bestPlayerStats.getStrength();

        for (int i = 1; i < players.size(); i++) {
            IPlayer player = players.get(i);
            IPlayerStats stats = player.getPlayerStats();
            float strength = stats.getStrength();

            if (strength > bestStrength) {
                bestPlayer = player;
                bestStrength = strength;
            }
        }

        LOGGER.info("The best draftee from the current list of draftees is " + bestPlayer.getPlayerName());
        return bestPlayer;
    }

    public void performDraftSelectionForTeam(ITeam teamPickingDraftee, List<IPlayer> draftees) {
        List<ITeamPlayer> teamPlayers = teamPickingDraftee.getPlayers();
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        IPlayer bestPlayer = this.findBestDraftee(draftees);
        draftees.remove(bestPlayer);

        teamPlayer.generateTeamPlayer(bestPlayer);
        teamPlayers.add(teamPlayer);
        LOGGER.info(teamPickingDraftee.getTeamName() + " picked " + teamPlayer.getPlayerName() + " from the current list of draftees...");
    }

    public List<ITeam> generateTeamOrderForDraftSelection(ILeague league) {
        List<ITeam> teamsForDraftRounds = new ArrayList<>();
        IStandingSystem standingSystem = league.getStandingSystem();
        List<IStanding> regularSeasonStandings = standingSystem.getRegularSeasonStandingsInReverse();
        int regularSeasonEliminatedTeamsCount = (regularSeasonStandings.size() / 2);
        int playOffCounter = regularSeasonEliminatedTeamsCount;

        IScheduleSystem scheduleSystem = league.getScheduleSystem();
        List<IStanding> playOffSeasonStandings = standingSystem.getPlayOffSeasonStandingsInReverse(scheduleSystem.getPlayoffSchedule());

        LOGGER.info("Generating team order for draft players selection...");
        for (int i = 0; i < regularSeasonEliminatedTeamsCount; i++) {
            IStanding standing = regularSeasonStandings.get(i);
            teamsForDraftRounds.add(standing.getTeam());
            LOGGER.info((i + 1) + " - " + standing.getTeam().getTeamName());
        }

        for (IStanding standing : playOffSeasonStandings) {
            playOffCounter++;
            teamsForDraftRounds.add(standing.getTeam());
            LOGGER.info((playOffCounter) + " - " + standing.getTeam().getTeamName());
        }

        return teamsForDraftRounds;
    }

    public List<ITeam> generateTeamOrderForDraftSelection(List<ITeam> teams, List<IDraftPick> draftPicks, int roundNumber) {
        List<ITeam> teamsForCurrentRound = new ArrayList<>(teams);
        List<IDraftPick> currentRoundDraftPicks = draftPicks.stream()
                .filter(draftPick -> draftPick.getDraftPickRoundNumber() == roundNumber).collect(Collectors.toList());

        for (IDraftPick draftPick : currentRoundDraftPicks) {
            ITeam teamTradedAwayTheirPick = draftPick.getSendingTeam();
            ITeam teamDoingAdditionalPick = draftPick.getReceivingTeam();

            int indexOfTeamToRemove = teamsForCurrentRound.indexOf(teamTradedAwayTheirPick);
            teamsForCurrentRound.remove(indexOfTeamToRemove);
            teamsForCurrentRound.add(indexOfTeamToRemove, teamDoingAdditionalPick);
        }

        return teamsForCurrentRound;
    }

}
