package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Scheduler.IScheduleSystem;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrengthCalculator;

import java.util.ArrayList;
import java.util.List;

public class DraftingState extends AbstractState {
    private static final int DRAFT_ROUNDS = 7;
    private final ILeagueManagerFactory leagueManagerFactory;
    private final IRandomPlayersGenerator randomPlayersGenerator;

    public DraftingState(IRandomPlayersGenerator randomPlayersGenerator) {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        this.randomPlayersGenerator = randomPlayersGenerator;
    }

    @Override
    public AbstractState onRun() {
        ILeague league = getLeague();
        List<ITeam> teamsForDraftRounds = generateTeamOrderForDraftSelection(league);
        int numberOfDrafteesPerRound = teamsForDraftRounds.size();

        for(int i = 0; i < DRAFT_ROUNDS; i++) {
            List<IPlayer> generatedPlayers = randomPlayersGenerator.generateRandomPlayers(league.getLeagueDate(), numberOfDrafteesPerRound);
        }

        for(ITeam teamPickingDraftees: teamsForDraftRounds) {
            ITeamStrengthCalculator teamStrengthCalculator = leagueManagerFactory.createTeamStrengthCalculator();
            List<ITeamPlayer> teamPlayers = teamPickingDraftees.getPlayers();

            teamPickingDraftees.setTeamStrength(teamStrengthCalculator.calculate(teamPlayers));

            for(ITeamPlayer teamPlayer: teamPlayers) {
                float strength = teamPlayer.calculateStrength(teamPlayer.getPlayerStats());
                IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
            }
        }
        return null;
    }

    private List<ITeam> generateTeamOrderForDraftSelection(ILeague league) {
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
}
