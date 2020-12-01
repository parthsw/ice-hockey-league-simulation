package com.IceHockeyLeagueTest.LeagueManagerTest.ManagerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.PersistenceTest.ManagerPersistenceMock;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ManagerTest {
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
    public void ConstructorTest() {
        IManager manager = leagueManagerFactory.createManager();
        Assert.assertEquals(-1, manager.getTeamID());
        Assert.assertEquals(-1, manager.getLeagueID());
        Assert.assertEquals(-1, manager.getManagerID());
    }

    @Test
    public void getManagerIDTest() {
        IManager manager = leagueManagerFactory.createManager();
        manager.setManagerID(2);
        Assert.assertEquals(2, manager.getManagerID());
    }

    @Test
    public void setManagerIDTest() {
        IManager manager = leagueManagerFactory.createManager();
        manager.setManagerID(8);
        Assert.assertEquals(8, manager.getManagerID());
    }

    @Test
    public void getManagerNameTest() {
        IManager manager = leagueManagerFactory.createManager();
        manager.setManagerName("J Hans");
        Assert.assertEquals("J Hans", manager.getManagerName());
    }

    @Test
    public void setManagerNameTest() {
        IManager manager = leagueManagerFactory.createManager();
        manager.setManagerName("Stevan K");
        Assert.assertEquals("Stevan K", manager.getManagerName());
    }

    @Test
    public void getTeamIDTest() {
        IManager manager = leagueManagerFactory.createManager();
        manager.setTeamID(2);
        Assert.assertEquals(2, manager.getTeamID());
    }

    @Test
    public void setTeamIDTest() {
        IManager manager = leagueManagerFactory.createManager();
        manager.setTeamID(8);
        Assert.assertEquals(8, manager.getTeamID());
    }

    @Test
    public void getLeagueIDTest() {
        IManager manager = leagueManagerFactory.createManager();
        manager.setLeagueID(2);
        Assert.assertEquals(2, manager.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        IManager manager = leagueManagerFactory.createManager();
        manager.setLeagueID(8);
        Assert.assertEquals(8, manager.getLeagueID());
    }

    @Test
    public void isNullOrEmptyTest() {
        IManager manager = leagueManagerFactory.createManager();
        Assert.assertTrue(manager.isNullOrEmpty(null));
        Assert.assertFalse(manager.isNullOrEmpty("James F"));
    }

    @Test
    public void isManagerNameExistTest() {
        IManager manager = leagueManagerFactory.createManager();
        ManagerPersistenceMock managerPersistenceMock =persistenceFactory.createManagerPersistence();
        List<IManager> managers = new ArrayList<>();
        managerPersistenceMock.loadLeagueManagers(1,managers);
        Assert.assertFalse(manager.isManagerNameExist(managers, "Harry P"));
        Assert.assertTrue(manager.isManagerNameExist(managers, "Fred one"));
    }
}
