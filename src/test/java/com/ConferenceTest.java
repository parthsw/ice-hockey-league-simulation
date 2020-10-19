package com;

import com.leaguemodel.models.Conference;
import com.leaguemodel.models.Division;
import com.leaguemodel.models.IDivision;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConferenceTest {

    @Test
    public void getConferenceNameTest(){
        Conference conference = new Conference();
        conference.setConferenceName("Eastern Conference");
        Assert.assertEquals("Eastern Conference", conference.getConferenceName());
    }

    @Test
    public void getConferenceIDTest(){
        Conference conference = new Conference();
        conference.setConferenceID(1);
        Assert.assertEquals(1, conference.getConferenceID());
    }

    @Test
    public void getDivisionByNameTest(){
        Conference conference = new Conference();
        IDivision division = new Division();
        division.setDivisionName("Division 1");
        conference.addDivision(division);
        Assert.assertEquals(division, conference.getDivisionByName("Division 1"));
    }

    @Test
    public void getDivisionsTest(){
        Conference conference = new Conference();
        List<IDivision> divisions = new ArrayList<>();

        IDivision division1 = new Division();
        division1.setDivisionName("Division 1");
        IDivision division2 = new Division();
        division2.setDivisionName("Division 2");

        divisions.add(division1);
        divisions.add(division2);

        conference.setDivisions(divisions);
        Assert.assertEquals(divisions, conference.getDivisions());
    }

}
