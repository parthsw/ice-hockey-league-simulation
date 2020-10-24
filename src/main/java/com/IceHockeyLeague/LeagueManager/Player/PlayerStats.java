package com.IceHockeyLeague.LeagueManager.Player;

public class PlayerStats implements IPlayerStats {
    private static final int STATS_LOWER_VALUE = 1;
    private static final int STATS_HIGHER_VALUE = 20;

    private String position;
    private int skating;
    private int shooting;
    private int checking;
    private int saving;
    private double strength;

    public PlayerStats() {
        setDefaults();
    }

    private void setDefaults() {
        position = "";
        skating = 0;
        shooting = 0;
        checking = 0;
        saving = 0;
        strength = 0;
    }

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
        if (isStatValid(skating)) {
            this.skating = skating;
        }
    }

    @Override
    public int getShooting() {
        return shooting;
    }

    @Override
    public void setShooting(int shooting) {
        if (isStatValid(shooting)) {
            this.shooting = shooting;
        }
    }

    @Override
    public int getChecking() {
        return checking;
    }

    @Override
    public void setChecking(int checking) {
        if (isStatValid(checking)) {
            this.checking = checking;
        }
    }

    @Override
    public int getSaving() {
        return saving;
    }

    @Override
    public void setSaving(int saving) {
        if (isStatValid(saving)) {
            this.saving = saving;
        }
    }

    @Override
    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Override
    public double getStrength() {
        return strength;
    }

    @Override
    public double calculateStrength() {
        double strength = 0;
        if(position.equalsIgnoreCase(PlayerPosition.FORWARD.toString())) {
            return forwardStrength();
        }
        else if(position.equalsIgnoreCase(PlayerPosition.DEFENSE.toString())) {
            return defenseStrength();
        }
        else if(position.equalsIgnoreCase(PlayerPosition.GOALIE.toString())) {
            return goalieStrength();
        }
        return strength;
    }

    private double forwardStrength() {
        double strength = 0;
        if(isStatValid(skating) && isStatValid(shooting) && isStatValid(checking)) {
            strength = skating + shooting + ((double)checking/2);
        }
        return strength;
    }

    private double defenseStrength() {
        double strength = 0;
        if(isStatValid(skating) && isStatValid(shooting) && isStatValid(checking)) {
            strength = skating + checking + ((double)shooting/2);
        }
        return strength;
    }

    private double goalieStrength() {
        double strength = 0;
        if(isStatValid(skating) && isStatValid(saving)) {
            strength = skating + saving;
        }
        return strength;
    }

    private boolean isStatValid(int statValue) {
        return (statValue >= STATS_LOWER_VALUE && statValue <= STATS_HIGHER_VALUE);
    }
}
