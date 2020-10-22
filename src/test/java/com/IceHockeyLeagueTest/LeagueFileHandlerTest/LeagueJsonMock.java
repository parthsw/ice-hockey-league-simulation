package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public JSONObject sampleLeagueJson() {

        JSONObject player = new JSONObject();
        player.put("playerName", "Player One");
        player.put("age", 34);
        player.put("position", "forward");
        player.put("captain", "true");
        player.put("skating", 17);
        player.put("shooting", 9);
        player.put("checking", 11);
        player.put("saving", 12);

        JSONArray players = new JSONArray();
        players.put(player);

        JSONObject coach = new JSONObject();
        coach.put("name", "Mary Smith");
        coach.put("skating", 0.5);
        coach.put("shooting", 0.8);
        coach.put("checking", 0.3);
        coach.put("saving", 0.5);

        // teams
        JSONObject team = new JSONObject();
        team.put("teamName", "Boston");
        team.put("generalManager", "Mister Fred");
        team.put("headCoach", coach);
        team.put("players", players);

        JSONArray teams = new JSONArray();
        teams.put(team);

        // divisions
        JSONObject division = new JSONObject();
        division.put("divisionName", "Atlantic");
        division.put("teams", teams);

        JSONArray divisions = new JSONArray();
        divisions.put(division);

        // conferences
        JSONObject conference = new JSONObject();
        conference.put("conferenceName", "Eastern Conference");
        conference.put("divisions", divisions);

        JSONArray conferences = new JSONArray();
        conferences.put(conference);

        // freeAgents
        JSONObject freeAgent = new JSONObject();
        freeAgent.put("playerName", "FreeAgent One");
        freeAgent.put("position", "forward");
        freeAgent.put("age", 25);
        freeAgent.put("skating", 10);
        freeAgent.put("shooting", 10);
        freeAgent.put("checking", 10);
        freeAgent.put("saving", 1);

        JSONArray freeAgents = new JSONArray();
        freeAgents.put(freeAgent);

        // coaches
        JSONObject freeCoach = new JSONObject();
        freeCoach.put("name", "Joe Smith");
        freeCoach.put("skating", 0.7);
        freeCoach.put("shooting", 0.3);
        freeCoach.put("checking", 0.6);
        freeCoach.put("saving", 0.2);

        JSONArray freeCoaches = new JSONArray();
        freeCoaches.put(freeCoach);

        // managers
        JSONArray managers = new JSONArray();
        managers.put("Manager 1");
        managers.put("Manager 2");
        managers.put("Manager 3");

        JSONObject leagueJson = new JSONObject();
        leagueJson.put("leagueName", "DHL");
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
}
