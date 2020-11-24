package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileReader;
import org.junit.*;
import org.junit.rules.TemporaryFolder;
import java.io.*;

public class LeagueFileReaderTest {
    private static ILeagueFileHandlerFactory leagueFileHandlerFactory;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueFileHandlerFactory(appFactory.createLeagueFileHandlerFactory());
        leagueFileHandlerFactory = AbstractAppFactory.getLeagueFileHandlerFactory();
    }

    @Test
    public void isFileExistFalseTest() {
        ILeagueFileReader leagueFileReader = leagueFileHandlerFactory.createLeagueFileReader();
        Assert.assertFalse(leagueFileReader.isFileExist(new File("")));
    }

    @Test
    public void isFileExistTrueTest() throws IOException {
        ILeagueFileReader leagueFileReader = leagueFileHandlerFactory.createLeagueFileReader();
        File tempFile = folder.newFile("test.json");
        Assert.assertTrue(leagueFileReader.isFileExist(tempFile));
    }

    @Test
    public void readSystemFileFalseTest() throws IOException {
        ILeagueFileReader leagueFileReader = leagueFileHandlerFactory.createLeagueFileReader();
        Assert.assertNull(leagueFileReader.readSystemFile(""));
    }

    @Test
    public void readSystemFileTrueTest() throws IOException {
        ILeagueFileReader leagueFileReader = leagueFileHandlerFactory.createLeagueFileReader();
        File systemFile = folder.newFile("systemFile.txt");
        Assert.assertTrue(leagueFileReader.readSystemFile(systemFile.getAbsolutePath()) instanceof FileInputStream);
    }

    @Test
    public void readAppResourceFileTest() {
        ILeagueFileReader leagueFileReader = leagueFileHandlerFactory.createLeagueFileReader();
        Assert.assertNull(leagueFileReader.readAppResourceFile(""));
        Assert.assertNull(leagueFileReader.readAppResourceFile("LeagueFile"));
    }

}
