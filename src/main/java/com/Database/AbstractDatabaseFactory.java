package com.Database;

public abstract  class AbstractDatabaseFactory {
    private static AbstractDatabaseFactory abstractDatabaseFactory;

    public static AbstractDatabaseFactory getFactory() {
        return abstractDatabaseFactory;
    }

    public static void setFactory(AbstractDatabaseFactory factory) {
        abstractDatabaseFactory = factory;
    }

    public abstract DBConnection getDBConnection();

    public abstract IDateConversion getSQLDateConversion();
}
