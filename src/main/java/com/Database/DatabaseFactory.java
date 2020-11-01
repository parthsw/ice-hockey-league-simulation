package com.Database;

public class DatabaseFactory extends AbstractDatabaseFactory {
    private DBConnection dbConnectionInstance = null;

    @Override
    public DBConnection getDBConnection() {
        if(dbConnectionInstance == null) {
            return new DBConnection();
        }
        return dbConnectionInstance;
    }

    public IStoredProcedure getStoredProcedure() {
        return new StoredProcedure();
    }

    @Override
    public IDateConversion getSQLDateConversion() {
        return new SQLDateConversion();
    }
}
