package asdc.hl.leaguemodel.models;

public interface IFreeAgent {
    int getPlayerID();
    void setPlayerID(int id);

    String getPlayerName();
    void setPlayerName(String name);

    boolean getIsCaptain();
    void setIsCaptain(boolean isCaptain);

    String getPlayerPosition();
    void setPlayerPosition(String name);


    String validateNameDuringImport();
    String validateNameDuringCreate();
    String validateNameDuringLoad();

    String validateBusinessRules();

//    void save();
//    void load();
}
