package com.IceHockeyLeagueTest.LeagueManagerTest.DraftTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPick;
import com.IceHockeyLeague.LeagueManager.Draft.IDraftManager;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DraftManagerTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;
    private static IDraftManager draftManager;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
        draftManager = leagueManagerFactory.createDraftManager();
    }

    @Test
    public void findBestDrafteeFoundTest() {
        IPlayer bestDraftee;
        ITeamPlayerPersistence teamPlayerPersistence = databaseFactory.createTeamPlayerPersistence();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        teamPlayerPersistence.loadTeamPlayers(1, teamPlayers);

        bestDraftee = draftManager.findBestDraftee(createPlayers(teamPlayers));
        Assert.assertEquals("Mike One", bestDraftee.getPlayerName());
    }

    @Test
    public void findBestDrafteeNotFoundTest() {
        List<IPlayer> players = new ArrayList<>();
        IPlayer bestDraftee = draftManager.findBestDraftee(players);
        Assert.assertNull(bestDraftee.getPlayerName());
    }

    @Test
    public void performDraftSelectionForTeamTest() {
        ITeamPlayerPersistence teamPlayerPersistence = databaseFactory.createTeamPlayerPersistence();
        ITeamPersistence teamPersistence = databaseFactory.createTeamPersistence();
        List<ITeam> teams = new ArrayList<>();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        ITeam teamPickingDraftee;
        List<IPlayer> draftees;

        teamPersistence.loadTeams(1, teams);
        teamPlayerPersistence.loadTeamPlayers(1, teamPlayers);
        teamPickingDraftee = teams.get(0);
        draftees = createPlayers(teamPlayers);

        draftManager.performDraftSelectionForTeam(teamPickingDraftee, draftees);
        Assert.assertEquals(3, teamPickingDraftee.getPlayers().size());
        Assert.assertEquals(1, draftees.size());
    }

    @Test
    public void generateTeamOrderForDraftSelectionTest() {
        ILeaguePersistence leaguePersistence = databaseFactory.createLeaguePersistence();
        ILeague league = leagueManagerFactory.createLeague();
        List<ITeam> draftRoundTeams;

        leaguePersistence.loadLeague(1, league);
        draftRoundTeams = draftManager.generateTeamOrderForDraftSelection(league);
        Assert.assertEquals(8, draftRoundTeams.size());
    }

    @Test
    public void generateTeamOrderForDraftSelectionRoundWiseTest() {
        ILeaguePersistence leaguePersistence = databaseFactory.createLeaguePersistence();
        ILeague league = leagueManagerFactory.createLeague();
        List<ITeam> draftRoundTeams;
        List<IDraftPick> draftPicks = new ArrayList<>();
        IDraftPick draftPick;
        List<ITeam> currentRoundTeams;

        leaguePersistence.loadLeague(1, league);
        draftRoundTeams = draftManager.generateTeamOrderForDraftSelection(league);
        draftPick = leagueManagerFactory.createDraftPick(draftRoundTeams.get(0), draftRoundTeams.get(2), 1, leagueManagerFactory.createTeamPlayer());
        draftPicks.add(draftPick);
        currentRoundTeams = draftManager.generateTeamOrderForDraftSelection(draftRoundTeams, draftPicks, 1);

        Assert.assertEquals(2, findNumberOfTeams(currentRoundTeams, draftRoundTeams.get(2)));
        Assert.assertEquals(0, findNumberOfTeams(currentRoundTeams, draftRoundTeams.get(0)));
    }

    private List<IPlayer> createPlayers(List<ITeamPlayer> teamPlayers) {
        List<IPlayer> players = new ArrayList<>();
        for (ITeamPlayer teamPlayer : teamPlayers) {
            IPlayer player = leagueManagerFactory.createPlayer();
            teamPlayer.generatePlayer(player);
            players.add(player);
        }
        return players;
    }

    private int findNumberOfTeams(List<ITeam> teams, ITeam matchingTeam) {
        return (int) teams.stream().filter(team -> team.equals(matchingTeam)).count();
    }

}
