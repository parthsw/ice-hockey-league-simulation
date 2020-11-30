package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;

import java.util.List;

public interface IGetBestAgent {

    IFreeAgent getBestAgentWithPosition(List<IFreeAgent> agents, String position);

    IFreeAgent getRandomBestAgentSkater(List<IFreeAgent> agents);
}
