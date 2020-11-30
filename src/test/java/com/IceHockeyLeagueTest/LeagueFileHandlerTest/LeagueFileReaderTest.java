package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileReader;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class LeagueFileReaderTest {
    private static ILeagueFileReader leagueFileReader;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setup() {
        ILeagueFileHandlerFactory leagueFileHandlerFactory;
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueFileHandlerFactory(appFactory.createLeagueFileHandlerFactory());
        leagueFileHandlerFactory = AbstractAppFactory.getLeagueFileHandlerFactory();
        leagueFileReader = leagueFileHandlerFactory.createLeagueFileReader();
    }

    @Test
    public void isFileExistFalseTest() {
        Assert.assertFalse(leagueFileReader.isFileExist(new File("")));
    }

    @Test
    public void isFileExistTrueTest() throws IOException {
        File tempFile = folder.newFile("test.json");
        Assert.assertTrue(leagueFileReader.isFileExist(tempFile));
    }

    @Test
    public void readSystemFileFalseTest() throws FileNotFoundException {
        thrown.expect(FileNotFoundException.class);
        leagueFileReader.readSystemFile("");
    }

    @Test
    public void readSystemFileTrueTest() throws IOException {
        File systemFile = folder.newFile("systemFile.txt");
        Assert.assertTrue(leagueFileReader.readSystemFile(systemFile.getAbsolutePath()) instanceof FileInputStream);
    }

    @Test
    public void readAppResourceFileTest() {
        Assert.assertNull(leagueFileReader.readAppResourceFile(""));
    }

}
