package com.IceHockeyLeague.LeagueManager.Player;

import java.time.LocalDate;
import java.util.List;

public interface IRandomPlayersGenerator {
    List<IPlayer> generateRandomPlayers(LocalDate currentDate, int totalPlayers);
}
