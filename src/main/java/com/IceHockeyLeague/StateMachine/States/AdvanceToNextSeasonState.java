package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IO.IAppOutput;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IGamePlayConfig;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Scheduler.IScheduleSystem;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.IStateMachine;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class AdvanceToNextSeasonState extends AbstractState {
    private static final String RETIRED_PLAYERS_START = "--------------- Retired Players -----------------";
    private static final String RETIRED_PLAYERS_END = "--------------- Retired Players end -------------";
    private static final String STANLEY_CUP_WINNER_START = "********** STANLEY CUP WINNER ************";
    private static final String STANLEY_CUP_WINNER_END = "******************************************";

    private final IAppOutput appOutput;
    private final ILeagueManagerFactory leagueManagerFactory;
    private final IStateMachineFactory stateMachineFactory;

    public AdvanceToNextSeasonState(IAppOutput appOutput) {
        this.appOutput = appOutput;
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
    }

    @Override
    public AbstractState onRun() {
        try {
            IRandomChance randomChance = leagueManagerFactory.createRandomChance();
            ILeague league = getLeague();
            IPlayerCareerProgression playerCareerProgression = leagueManagerFactory.createPlayerCareerProgression(randomChance);
            IRandomPlayersGenerator randomPlayersGenerator = leagueManagerFactory.createRandomPlayersGenerator(randomChance);

            IScheduleSystem scheduleSystem = league.getScheduleSystem();
            LocalDate regularSeasonStartDate = scheduleSystem.getRegularSeasonStartDate();
            int nextSeasonStartYear = regularSeasonStartDate.getYear() + 1;

            LocalDate retirementCheckingDate = LocalDate.of(nextSeasonStartYear, Month.MAY, 20);
            adjustLeaguePlayersAge(league, retirementCheckingDate);
            league.setLeagueDate(retirementCheckingDate);
            performLeaguePlayersRetirement(league, playerCareerProgression);

            LocalDate draftingDate = LocalDate.of(nextSeasonStartYear, Month.JULY, 15);
            adjustLeaguePlayersAge(league, draftingDate);
            league.setLeagueDate(draftingDate);

            AbstractState draftingState = stateMachineFactory.createDraftingState(randomPlayersGenerator);
            IStateMachine draftingSimulation = stateMachineFactory.createStateMachine(draftingState);
            draftingSimulation.onExecution();

            LocalDate newSeasonStartDate = LocalDate.of(nextSeasonStartYear, Month.SEPTEMBER, 29);
            adjustLeaguePlayersAge(league, newSeasonStartDate);
            league.setLeagueDate(newSeasonStartDate);

            appOutput.display(STANLEY_CUP_WINNER_START);
            appOutput.display(league.getScheduleSystem().getStanleyCupWinner().getTeamName());
            appOutput.display(STANLEY_CUP_WINNER_END);
        }
        catch(Exception e) {
            e.printStackTrace();
            appOutput.displayError(e.getMessage());
        }

        return stateMachineFactory.createPersistState();
    }

    private void adjustLeaguePlayersAge(ILeague league, LocalDate newDate) {
        LocalDate currentLeagueDate = league.getLeagueDate();
        int numberOfDaysElapsed = (int)DAYS.between(currentLeagueDate, newDate);

        for(IConference conference : league.getConferences()) {
            for(IDivision division : conference.getDivisions()) {
                for(ITeam team : division.getTeams()) {
                    for(ITeamPlayer teamPlayer : team.getPlayers()) {
                        teamPlayer.agePlayerByDays(numberOfDaysElapsed, currentLeagueDate);
                    }
                }
            }
        }

        for(IFreeAgent freeAgent : league.getFreeAgents()) {
            freeAgent.agePlayerByDays(numberOfDaysElapsed, currentLeagueDate);
        }

        for(ITeamPlayer retiredTeamPlayer : league.getRetiredTeamPlayers()) {
            retiredTeamPlayer.agePlayerByDays(numberOfDaysElapsed, currentLeagueDate);
        }

        for(IFreeAgent retiredFreeAgent : league.getRetiredFreeAgents()) {
            retiredFreeAgent.agePlayerByDays(numberOfDaysElapsed, currentLeagueDate);
        }
    }

    private void performLeaguePlayersRetirement(ILeague league, IPlayerCareerProgression playerCareerProgression) {
        IGamePlayConfig gamePlayConfig = league.getGamePlayConfig();

        appOutput.display(RETIRED_PLAYERS_START);
        for(IConference conference : league.getConferences()) {
            for(IDivision division : conference.getDivisions()) {
                for(ITeam team : division.getTeams()) {
                    List<ITeamPlayer> teamPlayers = team.getPlayers();
                    // using simple for loop instead of enhanced as I need to modify ArrayList during the iteration
                    for(int l = 0; l < teamPlayers.size(); l++) {
                        ITeamPlayer currentTeamPlayer = teamPlayers.get(l);
                        boolean isRetired = currentTeamPlayer.isRetired(playerCareerProgression, gamePlayConfig.getAgingConfig(), league.getLeagueDate());
                        if(isRetired) {
                            playerCareerProgression.handleTeamPlayerRetirement(currentTeamPlayer, team, league);
                            appOutput.display(currentTeamPlayer.getPlayerName());
                            if(l > 0) {
                                l--;
                            }
                        }
                    }
                }
            }
        }

        List<IFreeAgent> freeAgents = league.getFreeAgents();
        for(int i = 0; i < freeAgents.size(); i++) {
            IFreeAgent currentFreeAgent = freeAgents.get(i);
            boolean isRetired = currentFreeAgent.isRetired(playerCareerProgression, gamePlayConfig.getAgingConfig(), league.getLeagueDate());
            if(isRetired) {
                playerCareerProgression.handleFreeAgentRetirement(currentFreeAgent, league);
                appOutput.display(currentFreeAgent.getPlayerName());
                if(i > 0) {
                    i--;
                }
            }
        }
        appOutput.display(RETIRED_PLAYERS_END);
    }

}
