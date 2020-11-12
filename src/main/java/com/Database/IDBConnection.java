package com.Database;

import java.sql.Connection;

public interface IDBConnection {
    Connection getConnection();
    boolean terminateConnection();
}
