package com.IceHockeyLeague.LeagueManager.Player;

public interface IRandomChance {
    float getRandomFloatNumber(int lowerValue, int upperValue);
    float roundFloatNumber(float number, int noOfDecimalPlaces);
    int getRandomIntegerNumber(int lowerValue, int upperValue);
}
