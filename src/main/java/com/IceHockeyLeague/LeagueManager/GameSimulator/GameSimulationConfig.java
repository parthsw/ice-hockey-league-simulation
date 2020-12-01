package com.IceHockeyLeague.LeagueManager.GameSimulator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameSimulationConfig implements IGameSimulationConfig {
    private int averageGoalsPerTeam;
    private int averagePenaltiesPerTeam;
    private int averageShotsPerTeam;
    private int averageSavesPerTeam;

    private static final Logger LOGGER = LogManager.getLogger(GameSimulationConfig.class);

    public GameSimulationConfig() {
        getConfigValues();
    }

    @Override
    public int getAverageGoalsPerTeam() {
        return averageGoalsPerTeam;
    }

    @Override
    public void setAverageGoalsPerTeam(int averageGoalsPerTeam) {
        this.averageGoalsPerTeam = averageGoalsPerTeam;
    }

    @Override
    public int getAveragePenaltiesPerTeam() {
        return averagePenaltiesPerTeam;
    }

    @Override
    public void setAveragePenaltiesPerTeam(int averagePenaltiesPerTeam) {
        this.averagePenaltiesPerTeam = averagePenaltiesPerTeam;
    }

    @Override
    public int getAverageShotsPerTeam() {
        return averageShotsPerTeam;
    }

    @Override
    public void setAverageShotsPerTeam(int averageShotsPerTeam) {
        this.averageShotsPerTeam = averageShotsPerTeam;
    }

    @Override
    public int getAverageSavesPerTeam() {
        return averageSavesPerTeam;
    }

    @Override
    public void setAverageSavesPerTeam(int averageSavesPerTeam) {
        this.averageSavesPerTeam = averageSavesPerTeam;
    }

    private void getConfigValues() {
        // Code referred from https://mkyong.com/java/java-properties-file-examples/
        try (InputStream input = new FileInputStream("src/main/resources/DHLAverageStats.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            averageGoalsPerTeam = Math.round(Float.parseFloat(prop.getProperty("averageGoalsPerTeam")));
            averagePenaltiesPerTeam = Math.round(Float.parseFloat(prop.getProperty("averagePenaltiesPerTeam")));
            averageShotsPerTeam = Math.round(Float.parseFloat(prop.getProperty("averageShotsPerTeam")));
            averageSavesPerTeam = Math.round(Float.parseFloat(prop.getProperty("averageSavesPerTeam")));

            LOGGER.info("average Goals per team in a game: " + averageGoalsPerTeam);
            LOGGER.info("average Penalties per team in a game: " + averagePenaltiesPerTeam);
            LOGGER.info("average Shots per team in a game: " + averageShotsPerTeam);
            LOGGER.info("average Saves per team in a game: " + averageSavesPerTeam);

        } catch (FileNotFoundException ex) {
            LOGGER.error("DHL Averages properties file not found", ex);
        } catch (IOException ex) {
            LOGGER.error("Could not read DHL Averages properties file", ex);
        }
    }
}
