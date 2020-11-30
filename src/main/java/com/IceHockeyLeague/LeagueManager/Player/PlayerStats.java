package com.IceHockeyLeague.LeagueManager.Player;

import com.IceHockeyLeague.LeagueManager.GamePlayConfig.IAgingConfig;

public class PlayerStats implements IPlayerStats {
    private static final int STATS_LOWER_VALUE = 1;
    private static final int STATS_HIGHER_VALUE = 20;

    private String position;
    private int skating;
    private int shooting;
    private int checking;
    private int saving;
    private float strength;

    public PlayerStats() {
        setDefaults();
    }

    private void setDefaults() {
        position = "";
        skating = 0;
        shooting = 0;
        checking = 0;
        saving = 0;
        strength = 0f;
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
    public void setStrength(float strength) {
        this.strength = strength;
    }

    @Override
    public float getStrength() {
        return strength;
    }

    @Override
    public float calculateStrength() {
        float strength = 0f;
        if (position.equalsIgnoreCase(PlayerPosition.FORWARD.toString())) {
            return forwardStrength();
        } else if (position.equalsIgnoreCase(PlayerPosition.DEFENSE.toString())) {
            return defenseStrength();
        } else if (position.equalsIgnoreCase(PlayerPosition.GOALIE.toString())) {
            return goalieStrength();
        }
        return strength;
    }

    @Override
    public void performStatDecay(IAgingConfig agingConfig, IRandomChance randomChance) {
        float statDecayChance = agingConfig.getStatDecayChance();
        skating = handleStatDecay(skating, statDecayChance, randomChance);
        shooting = handleStatDecay(shooting, statDecayChance, randomChance);
        checking = handleStatDecay(checking, statDecayChance, randomChance);
        saving = handleStatDecay(saving, statDecayChance, randomChance);
    }

    private float forwardStrength() {
        float strength = 0f;
        if (isStatValid(skating) && isStatValid(shooting) && isStatValid(checking)) {
            strength = skating + shooting + ((float) checking / 2);
        }
        return strength;
    }

    private float defenseStrength() {
        float strength = 0f;
        if (isStatValid(skating) && isStatValid(shooting) && isStatValid(checking)) {
            strength = skating + checking + ((float) shooting / 2);
        }
        return strength;
    }

    private float goalieStrength() {
        float strength = 0f;
        if (isStatValid(skating) && isStatValid(saving)) {
            strength = skating + saving;
        }
        return strength;
    }

    private boolean isStatValid(int statValue) {
        return (statValue >= STATS_LOWER_VALUE && statValue <= STATS_HIGHER_VALUE);
    }

    private boolean shouldStatBeDecayed(IRandomChance randomChance, float statDecayChance) {
        return randomChance.getRandomFloatNumber(0, 1) < statDecayChance;
    }

    private int handleStatDecay(int stat, float statDecayChance, IRandomChance randomChance) {
        if (shouldStatBeDecayed(randomChance, statDecayChance) && isStatValid(stat)) {
            stat = stat - 1;
        }
        return stat;
    }

}
