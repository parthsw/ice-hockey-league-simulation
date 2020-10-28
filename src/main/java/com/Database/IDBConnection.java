package com.Database;

import java.sql.Connection;

public interface IDBConnection {
    public Connection getConnection();
    public boolean terminateConnection();
}
