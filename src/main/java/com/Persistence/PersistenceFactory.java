package com.Persistence;

public class PersistenceFactory implements IPersistenceFactory{

    @Override
    public ILeaguePersistence createLeaguePersistence() {
        return new LeaguePersistence();
    }
}