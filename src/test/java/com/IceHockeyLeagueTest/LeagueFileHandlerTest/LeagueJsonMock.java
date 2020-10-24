package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class LeagueJsonMock {

    private static LeagueJsonMock leagueJsonMock;

    public static LeagueJsonMock getInstance() {
        if (leagueJsonMock == null) {
            leagueJsonMock = new LeagueJsonMock();
        }
        return leagueJsonMock;
    }

    public JSONObject leagueSchema() {
        JSONArray requiredProperties = new JSONArray();
        requiredProperties.put("leagueName");

        JSONObject nonEmptyString = new JSONObject();
        nonEmptyString.put("type", "string");
        nonEmptyString.put("minLength", 1);

        JSONObject properties = new JSONObject();
        properties.put("leagueName", nonEmptyString);

        JSONObject schema = new JSONObject();
        schema.put("$schema", "http://json-schema.org/draft-06/schema#");
        schema.put("required", requiredProperties);
        schema.put("type", "object");
        schema.put("properties", properties);

        return schema;
    }

    public JSONObject validLeagueJson() {

        JSONArray players = new JSONArray();
        for (int i=0; i<20; i++) {
            JSONObject player = new JSONObject();
            player.put("playerName", "Player One");
            player.put("age", 34);
            player.put("position", "forward");
            player.put("captain", true);
            player.put("skating", 17);
            player.put("shooting", 9);
            player.put("checking", 11);
            player.put("saving", 12);
            players.put(player);
        }

        JSONObject coach = new JSONObject();
        coach.put("name", "Mary Smith");
        coach.put("skating", 0.5);
        coach.put("shooting", 0.8);
        coach.put("checking", 0.3);
        coach.put("saving", 0.5);

        JSONArray teams = new JSONArray();
        for (int i=0; i<2; i++) {
            JSONObject team = new JSONObject();
            team.put("teamName", "Boston");
            team.put("generalManager", "Mister Fred");
            team.put("headCoach", coach);
            team.put("players", players);
            teams.put(team);
        }

        JSONArray divisions = new JSONArray();
        for (int i=0; i<2; i++) {
            JSONObject division = new JSONObject();
            division.put("divisionName", "Atlantic");
            division.put("teams", teams);
            divisions.put(division);
        }

        JSONArray conferences = new JSONArray();
        for (int i=0; i<2; i++) {
            JSONObject conference = new JSONObject();
            conference.put("conferenceName", "Eastern Conference");
            conference.put("divisions", divisions);
            conferences.put(conference);
        }

        JSONArray freeAgents = new JSONArray();
        for (int i=0; i<3; i++) {
            JSONObject freeAgent = new JSONObject();
            freeAgent.put("playerName", "FreeAgent One");
            freeAgent.put("position", "forward");
            freeAgent.put("age", 25);
            freeAgent.put("skating", 10);
            freeAgent.put("shooting", 10);
            freeAgent.put("checking", 10);
            freeAgent.put("saving", 1);
            freeAgents.put(freeAgent);
        }

        JSONArray freeCoaches = new JSONArray();
        for (int i=0; i<3; i++) {
            JSONObject freeCoach = new JSONObject();
            freeCoach.put("name", "Joe Smith");
            freeCoach.put("skating", 0.7);
            freeCoach.put("shooting", 0.3);
            freeCoach.put("checking", 0.6);
            freeCoach.put("saving", 0.2);
            freeCoaches.put(freeCoach);
        }

        JSONArray managers = new JSONArray();
        for (int i=0; i<3; i++) {
            managers.put("Manager ");
        }

        JSONObject aging = new JSONObject();
        aging.put("averageRetirementAge", 35);
        aging.put("maximumAge", 50);

        JSONObject gameResolver = new JSONObject();
        gameResolver.put("randomWinChance", 0.1);

        JSONObject injuries = new JSONObject();
        injuries.put("randomInjuryChance", 0.05);
        injuries.put("injuryDaysLow", 1);
        injuries.put("injuryDaysHigh", 260);

        JSONObject training = new JSONObject();
        training.put("daysUntilStatIncreaseCheck", 100);

        JSONObject trading = new JSONObject();
        trading.put("lossPoint", 8);
        trading.put("randomTradeOfferChance", 0.05);
        trading.put("maxPlayersPerTrade", 2);
        trading.put("randomAcceptanceChance", 0.05);

        JSONObject gameplayConfig = new JSONObject();
        gameplayConfig.put("aging", aging);
        gameplayConfig.put("gameResolver", gameResolver);
        gameplayConfig.put("injuries", injuries);
        gameplayConfig.put("training", training);
        gameplayConfig.put("trading", trading);


        JSONObject leagueJson = new JSONObject();
        leagueJson.put("leagueName", "DHL");
        leagueJson.put("gameplayConfig", gameplayConfig);
        leagueJson.put("conferences", conferences);
        leagueJson.put("freeAgents", freeAgents);
        leagueJson.put("coaches", freeCoaches);
        leagueJson.put("generalManagers", managers);

        return leagueJson;
    }

    public JSONObject invalidLeagueJson() {
        JSONObject invalidLeagueJson = new JSONObject();
        invalidLeagueJson.put("leagueName", "");

        return invalidLeagueJson;
    }

    public String createLeagueJsonFile(File file, JSONObject jsonObject) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(jsonObject.toString());
            writer.flush();
            return file.getPath();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
