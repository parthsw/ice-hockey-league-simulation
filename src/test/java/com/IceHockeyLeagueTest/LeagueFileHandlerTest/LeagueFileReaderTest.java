package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileReader;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class LeagueFileReaderTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @BeforeClass
    public static void setup() {
        AbstractLeagueFileHandlerFactory.setFactory(new LeagueFileHandlerFactory());
    }

    @Test
    public void isFileExistTest() throws IOException {
        ILeagueFileReader leagueFileReader = AbstractLeagueFileHandlerFactory.getFactory().getLeagueFileReader();
        Assert.assertFalse(leagueFileReader.isFileExist(new File("")));

        File tempFile = folder.newFile("test.json");
        Assert.assertTrue(leagueFileReader.isFileExist(tempFile));
    }

    @Test
    public void readSystemFileTest() throws IOException {
        ILeagueFileReader leagueFileReader = AbstractLeagueFileHandlerFactory.getFactory().getLeagueFileReader();
        File systemFile = folder.newFile("systemFile.txt");

        Assert.assertTrue(leagueFileReader.readSystemFile(systemFile.getAbsolutePath()) instanceof FileInputStream);
    }

    @Test
    public void readAppResourceFileTest() {
        ILeagueFileReader leagueFileReader = AbstractLeagueFileHandlerFactory.getFactory().getLeagueFileReader();

        Assert.assertNull(leagueFileReader.readAppResourceFile(""));
        Assert.assertNull(leagueFileReader.readAppResourceFile("LeagueFile"));
    }
}
