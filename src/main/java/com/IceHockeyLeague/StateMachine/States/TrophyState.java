package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.TrophySystem.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrophyState extends AbstractState {

    private final IStateMachineFactory stateMachineFactory;
    private IAwardDistributed awardDisplay;
    private IAwardWinners awardWinner;
    List<String> awardsWinnerList;
    ITrophySystemFactory trophySystemFactory;


    public TrophyState(){
        ILeague league = getLeague();
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        trophySystemFactory = AbstractAppFactory.getTrophySystemFactory();
        awardDisplay = trophySystemFactory.displayAwards();
        awardWinner = trophySystemFactory.trophyDistribution();
    }

    @Override
    public AbstractState onRun() {
        AbstractState nextState;
        startAwards();
        awardDistributionInProcess();
        nextState = stateMachineFactory.createAdvanceToNextSeasonState();
        return nextState;
    }

  public void startAwards(){
        ILeague league = this.getLeague();
        LocalDate leagueDate = league.getLeagueDate();
        awardsWinnerList.add(awardWinner.getLowestPointsTeam());
        awardsWinnerList.add(awardWinner.getBestDefenceMen());
        awardsWinnerList.add(awardWinner.getTopGoalScorer());
        awardsWinnerList.add(awardWinner.getBestGoalie());
        awardsWinnerList.add(awardWinner.getBestPlayer());
        awardsWinnerList.add(awardWinner.getHighestPointsTeam());
        awardsWinnerList.add(leagueDate.toString());
    }

    public void awardDistributionInProcess() {
        for (int i = awardsWinnerList.size() - 1; i >= 0; i--) {
            awardDisplay.presidentTrophy(awardsWinnerList.get(i + 1));
            awardDisplay.calderMemorialTrophy(awardsWinnerList.get(i + 2));
            awardDisplay.vezinaTrophy(awardsWinnerList.get(i + 3));
            awardDisplay.mauriceRichardTrophy(awardsWinnerList.get(i + 4));
            awardDisplay.robHawkeyMemorialCup(awardsWinnerList.get(i + 5));
            awardDisplay.participationAward(awardsWinnerList.get(i + 6));
        }
    }
}
