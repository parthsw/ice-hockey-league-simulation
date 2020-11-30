package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
//import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
//import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrengthCalculator;
import com.PersistenceTest.PersistenceFactoryTest;
import com.PersistenceTest.TeamPlayerPersistenceMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TeamStrengthCalculatorTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    @Test
    public void calculateTest() {
        TeamPlayerPersistenceMock teamPlayerPersistenceMock = persistenceFactory.createTeamPlayerPersistence();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        teamPlayerPersistenceMock.loadTeamPlayers(1,teamPlayers);

        ITeamStrengthCalculator teamStrength = leagueManagerFactory.createTeamStrengthCalculator();
        Assert.assertEquals(46.5f, teamStrength.calculate(teamPlayers), 0.0);
    }
}
