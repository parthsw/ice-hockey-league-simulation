package asdc.hl;

import asdc.hl.leaguemodel.IPersistence;
import asdc.hl.leaguemodel.models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TeamTest {

    @Test
    public void getTeamNameTest(){
        Team team = new Team();
        team.setTeamName("Team 1");
        Assert.assertEquals("Team 1", team.getTeamName());
    }

    @Test
    public void getTeamIDTest(){
        Team team = new Team();
        team.setTeamID(1);
        Assert.assertEquals(1, team.getTeamID());
    }

    @Test
    public void getPlayerByNameTest(){
        Team team = new Team();
        IPlayer player = new Player();
        player.setPlayerName("Player 1");
        team.addPlayer(player);
        Assert.assertEquals(player, team.getPlayerByName("Player 1"));
    }

    @Test
    public void getTeamsTest(){
        Team team = new Team();
        List<IPlayer> players = new ArrayList<>();

        IPlayer player1 = new Player();
        player1.setPlayerName("Player 1");
        IPlayer player2 = new Player();
        player2.setPlayerName("Player 2");

        players.add(player1);
        players.add(player2);

        team.setPlayers(players);
        Assert.assertEquals(players, team.getPlayers());
    }
}
