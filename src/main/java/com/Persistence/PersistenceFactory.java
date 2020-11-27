package com.Persistence;

public class PersistenceFactory implements IPersistenceFactory{

    @Override
    public ILeaguePersistece createLeaguePersistence() {
        return new LeaguePersistence();
    }
}