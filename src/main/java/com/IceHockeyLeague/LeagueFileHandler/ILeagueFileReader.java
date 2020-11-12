package com.IceHockeyLeague.LeagueFileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ILeagueFileReader {

    boolean isFileExist(File file);
    InputStream readSystemFile(String filePath) throws FileNotFoundException;
    InputStream readAppResourceFile(String resourceFileName);

}
