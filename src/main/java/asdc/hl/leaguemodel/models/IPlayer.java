package asdc.hl.leaguemodel.models;

public interface IPlayer {
    int getPlayerID();
    void setPlayerID(int id);

    String getPlayerName();
    void setPlayerName(String name);

    boolean getIsCaptain();
    void setIsCaptain(boolean isCaptain);

    String getPlayerPosition();
    void setPlayerPosition(String name);

    int getTeamID();
    void setTeamID(int id);

    String validateNameDuringImport();
    String validateNameDuringCreate();
    String validateNameDuringLoad();

    String validateBusinessRules();

    void save();
    void load();
}
