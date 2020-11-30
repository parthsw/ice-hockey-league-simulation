package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest.RosterTest;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;

import java.util.List;

public interface IGenerateMockObjectForRosterTesting {
    List<IFreeAgent> getAgentList(int number);

    List<ITeamPlayer> getPlayerList(int number);
}
