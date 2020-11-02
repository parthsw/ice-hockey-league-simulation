package com.Trading;

import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import org.junit.Test;


public class SimulateTradeTest {

    @Test
    public void simulateTest() {
        SimulateTrade simulation = new SimulateTrade();
        ILeague league = new League();
        IConference conference = new Conference();
        IDivision division = new Division();
        league.addConference(conference);
        conference.addDivision(division);

        for (int i = 0; i < 20; i++) {
        }

//        for(int i = 0; i < 20; i++){
//            IConference conference = new Conference();
//            league.addConference(conference);
//            for(int j =0; j<20; j++){
//                IDivision division = new Division();
//                conference.addDivision(division);
//                for(int k=0; k<20;k++){
//                    ITeam team = new Team();
//
//                }
//            }
//        }

    }
}
