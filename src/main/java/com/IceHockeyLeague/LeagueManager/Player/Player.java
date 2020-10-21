package com.IceHockeyLeague.LeagueManager.Player;

public class Player implements IPlayer {
    private int playerID;
    private String playerName;
    private boolean isCaptain;
    private String position;
    private int teamID;
    private int age;
    private int skating;
    private int shooting;
    private int checking;
    private int saving;

    @Override
    public int getPlayerID() {
        return 0;
    }

    @Override
    public void setPlayerID(int id) {

    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public void setPlayerName(String name) {

    }

    @Override
    public boolean getIsCaptain() {
        return false;
    }

    @Override
    public void setIsCaptain(boolean isCaptain) {

    }

    @Override
    public String getPosition() {
        return null;
    }

    @Override
    public void setPosition(String name) {

    }

    @Override
    public int getTeamID() {
        return 0;
    }

    @Override
    public void setTeamID(int id) {

    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public void setAge(int age) {

    }

    @Override
    public int getSkating() {
        return 0;
    }

    @Override
    public void setSkating(int value) {

    }

    @Override
    public int getShooting() {
        return 0;
    }

    @Override
    public void setShooting(int value) {

    }

    @Override
    public int getChecking() {
        return 0;
    }

    @Override
    public void setChecking(int value) {

    }

    @Override
    public int getSaving() {
        return 0;
    }

    @Override
    public void setSaving(int value) {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
