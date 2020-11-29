package com.IceHockeyLeague.StateMachine.States;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.TrophySystem.AwardCeremony;
import com.TrophySystem.DisplayAwards;
import com.TrophySystem.Interfaces.IAwardDistributed;
import com.TrophySystem.IAwardWinners;

import java.time.LocalDate;
import java.util.List;

public class TrophyState extends AbstractState {

    private final IStateMachineFactory stateMachineFactory;
    private IAwardDistributed awardDisplay;
    private IAwardWinners awardWinner;
    List<String> awardsWinnerList;

    public TrophyState(){
        ILeague league = getLeague();
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        awardDisplay = new DisplayAwards();
        awardWinner = new AwardCeremony();
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
        awardsWinnerList.add(awardWinner.getBestCoach());
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
            awardDisplay.jackAdamsAward(awardsWinnerList.get(i + 4));
            awardDisplay.mauriceRichardTrophy(awardsWinnerList.get(i + 5));
            awardDisplay.robHawkeyMemorialCup(awardsWinnerList.get(i + 6));
            awardDisplay.participationAward(awardsWinnerList.get(i + 7));
        }
    }
}
