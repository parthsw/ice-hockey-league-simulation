package com.IceHockeyLeague.LeagueManager.Player;

public class PlayerStats implements IPlayerStats {
    private String position;
    private int skating;
    private int shooting;
    private int checking;
    private int saving;
    private int strength;

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int getSkating() {
        return skating;
    }

    @Override
    public void setSkating(int skating) {
        this.skating = skating;
    }

    @Override
    public int getShooting() {
        return shooting;
    }

    @Override
    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    @Override
    public int getChecking() {
        return checking;
    }

    @Override
    public void setChecking(int checking) {
        this.checking = checking;
    }

    @Override
    public int getSaving() {
        return saving;
    }

    @Override
    public void setSaving(int saving) {
        this.saving = saving;
    }

    @Override
    public int getPlayerStrength() {
        return strength;
    }

    public void setPlayerStrength(int strength) {
        this.strength = strength;
    }

}
