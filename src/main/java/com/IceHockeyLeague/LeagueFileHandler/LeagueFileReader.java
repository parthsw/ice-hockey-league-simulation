package com.IceHockeyLeague.LeagueFileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LeagueFileReader implements ILeagueFileReader {

    @Override
    public boolean isFileExist(File file) {
        return file.exists() && file.isFile();
    }

    @Override
    public InputStream readSystemFile(String filePath) throws FileNotFoundException {
        if(filePath.isEmpty()) {
            return null;
        }
        return new FileInputStream(filePath);
    }

    @Override
    public InputStream readAppResourceFile(String resourceFileName) {
        if(resourceFileName.isEmpty()) {
            return null;
        }
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(resourceFileName);
    }
}
