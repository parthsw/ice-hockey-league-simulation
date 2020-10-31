package com.IceHockeyLeagueTest.LeagueManagerTest.ManagerTest;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ManagerTest {
    private static AbstractLeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
        leagueManagerFactory = AbstractLeagueManagerFactory.getFactory();
    }

    @Test
    public void ConstructorTest() {
        IManager manager = leagueManagerFactory.getManager();
        Assert.assertEquals(-1, manager.getTeamID());
        Assert.assertEquals(-1, manager.getLeagueID());
        Assert.assertEquals(-1, manager.getManagerID());
    }

    @Test
    public void getManagerIDTest() {
        IManager manager = leagueManagerFactory.getManager();
        manager.setManagerID(2);
        Assert.assertEquals(2, manager.getManagerID());
    }

    @Test
    public void setManagerIDTest() {
        IManager manager = leagueManagerFactory.getManager();
        manager.setManagerID(8);
        Assert.assertEquals(8, manager.getManagerID());
    }

    @Test
    public void getManagerNameTest() {
        IManager manager = leagueManagerFactory.getManager();
        manager.setManagerName("J Hans");
        Assert.assertEquals("J Hans", manager.getManagerName());
    }

    @Test
    public void setManagerNameTest() {
        IManager manager = leagueManagerFactory.getManager();
        manager.setManagerName("Stevan K");
        Assert.assertEquals("Stevan K", manager.getManagerName());
    }

    @Test
    public void getTeamIDTest() {
        IManager manager = leagueManagerFactory.getManager();
        manager.setTeamID(2);
        Assert.assertEquals(2, manager.getTeamID());
    }

    @Test
    public void setTeamIDTest() {
        IManager manager = leagueManagerFactory.getManager();
        manager.setTeamID(8);
        Assert.assertEquals(8, manager.getTeamID());
    }

    @Test
    public void getLeagueIDTest() {
        IManager manager = leagueManagerFactory.getManager();
        manager.setLeagueID(2);
        Assert.assertEquals(2, manager.getLeagueID());
    }

    @Test
    public void setLeagueIDTest() {
        IManager manager = leagueManagerFactory.getManager();
        manager.setLeagueID(8);
        Assert.assertEquals(8, manager.getLeagueID());
    }

    @Test
    public void saveTeamManagerTest() {
        IManager manager = leagueManagerFactory.getManager();
        IManagerPersistence managerDB = leagueManagerFactory.getManagerDB();

        Assert.assertTrue(manager.saveTeamManager(managerDB));
        Assert.assertEquals(1, manager.getTeamID());
        Assert.assertEquals("Joseph Hans", manager.getManagerName());
    }

    @Test
    public void saveLeagueManagerTest() {
        IManager manager = leagueManagerFactory.getManager();
        IManagerPersistence managerDB = leagueManagerFactory.getManagerDB();

        Assert.assertTrue(manager.saveLeagueManager(managerDB));
        Assert.assertEquals(-1, manager.getTeamID());
        Assert.assertEquals("Roy K", manager.getManagerName());
    }

    @Test
    public void loadTeamManagerTest() {
        IManager manager = leagueManagerFactory.getManager();
        IManagerPersistence managerDB = leagueManagerFactory.getManagerDB();

        Assert.assertTrue(manager.loadTeamManager(managerDB, manager));
        Assert.assertEquals(1, manager.getTeamID());
        Assert.assertEquals(1, manager.getManagerID());
        Assert.assertEquals("Joseph Spaghetti", manager.getManagerName());
    }

    @Test
    public void isNullOrEmptyTest() {
        IManager manager = leagueManagerFactory.getManager();
        Assert.assertTrue(manager.isNullOrEmpty(null));
        Assert.assertFalse(manager.isNullOrEmpty("James F"));
    }

    @Test
    public void isManagerNameExistTest() {
        IManager manager = leagueManagerFactory.getManager();
        IManagerPersistence managerDB = leagueManagerFactory.getManagerDB();
        List<IManager> managers = new ArrayList<>();
        managerDB.loadLeagueManagers(1, managers);

        Assert.assertFalse(manager.isManagerNameExist(managers, "Harry P"));
        Assert.assertTrue(manager.isManagerNameExist(managers, "Fred one"));
    }
}
