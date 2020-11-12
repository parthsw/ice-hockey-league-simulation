package com.DatabaseTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;

import java.time.LocalDate;
import java.util.List;

public class FreeAgentPersistenceMock implements IFreeAgentPersistence {
    private final ILeagueManagerFactory leagueManagerFactory;

    public FreeAgentPersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    @Override
    public boolean saveFreeAgent(IFreeAgent freeAgent) {
        freeAgent.setLeagueID(1);
        freeAgent.setFreeAgentID(1);
        freeAgent.setPlayerName("Mike Two");
        freeAgent.setPlayerAge(24);
        freeAgent.setElapsedDaysFromLastBDay(123);
        freeAgent.setInjuredStatus(false);
        freeAgent.setDaysInjured(0);
        freeAgent.setInjuryDate(null);
        freeAgent.setRetiredStatus(false);
        freeAgent.setRetirementDate(null);

        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setPosition("forward");
        stats.setShooting(10);
        stats.setChecking(2);
        stats.setSaving(14);
        stats.setSkating(18);
        stats.setStrength(29);
        freeAgent.setPlayerStats(stats);
        return true;
    }

    @Override
    public boolean loadFreeAgents(int leagueId, List<IFreeAgent> freeAgents) {
        IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
        freeAgent.setFreeAgentID(1);
        freeAgent.setLeagueID(1);
        freeAgent.setPlayerName("Fred One");
        freeAgent.setPlayerAge(23);
        freeAgent.setElapsedDaysFromLastBDay(212);
        freeAgent.setInjuredStatus(false);
        freeAgent.setDaysInjured(0);
        freeAgent.setInjuryDate(null);
        freeAgent.setRetiredStatus(false);
        freeAgent.setRetirementDate(null);

        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        stats.setPosition("forward");
        stats.setShooting(10);
        stats.setChecking(2);
        stats.setSaving(14);
        stats.setSkating(18);
        stats.setStrength(29);
        freeAgent.setPlayerStats(stats);

        IFreeAgent freeAgent1 = leagueManagerFactory.createFreeAgent();
        freeAgent1.setFreeAgentID(2);
        freeAgent1.setLeagueID(1);
        freeAgent1.setPlayerName("Mike One");
        freeAgent1.setPlayerAge(27);
        freeAgent1.setElapsedDaysFromLastBDay(22);
        freeAgent1.setInjuredStatus(true);
        freeAgent1.setDaysInjured(23);
        freeAgent1.setInjuryDate(LocalDate.of(2020, 10, 20));
        freeAgent1.setRetiredStatus(false);
        freeAgent1.setRetirementDate(null);

        IPlayerStats stats1 = leagueManagerFactory.createPlayerStats();
        stats1.setPosition("defense");
        stats1.setShooting(12);
        stats1.setChecking(18);
        stats1.setSaving(16);
        stats1.setSkating(11);
        stats1.setStrength(35);
        freeAgent1.setPlayerStats(stats1);

        freeAgents.add(freeAgent);
        freeAgents.add(freeAgent1);

        IFreeAgent freeAgent2 = leagueManagerFactory.createFreeAgent();
        freeAgent2.setFreeAgentID(3);
        freeAgent2.setLeagueID(1);
        freeAgent2.setPlayerName("Fred Two");
        freeAgent2.setPlayerAge(29);
        freeAgent2.setElapsedDaysFromLastBDay(212);
        freeAgent2.setInjuredStatus(false);
        freeAgent2.setDaysInjured(0);
        freeAgent2.setInjuryDate(null);
        freeAgent2.setRetiredStatus(false);
        freeAgent2.setRetirementDate(null);

        IPlayerStats stats2 = leagueManagerFactory.createPlayerStats();
        stats2.setPosition("forward");
        stats2.setShooting(19);
        stats2.setChecking(13);
        stats2.setSaving(16);
        stats2.setSkating(18);
        stats2.setStrength(43.5f);
        freeAgent2.setPlayerStats(stats2);
        freeAgents.add(freeAgent2);

        return true;
    }
}
