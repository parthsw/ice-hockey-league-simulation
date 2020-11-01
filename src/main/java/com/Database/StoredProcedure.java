package com.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StoredProcedure implements IStoredProcedure {
    private final Connection connection;
    private CallableStatement callableStatement;
    private final IDBConnection connectionManager;

    public StoredProcedure() {
        connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
        connection = connectionManager.getConnection();
    }

    public CallableStatement setup(String procedureName) throws SQLException {
        callableStatement = connection.prepareCall("{call " + procedureName + "}");
        return callableStatement;
    }

    public void cleanup() {
        try {
            if(connection == null) {
                return;
            } else {
                if(connection.isClosed() == false) {
                    connection.close();
                }
            }

            if(callableStatement == null) {
            } else {
                callableStatement.close();
            }
        }
        catch (Exception e) {
            System.out.println("Error while performing connection cleanup: " + e.getMessage());
        }
    }

}
