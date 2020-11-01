package com.Database;

import java.sql.CallableStatement;
import java.sql.SQLException;

public interface IStoredProcedure {
    CallableStatement setup(String procedureName) throws SQLException;
    void cleanup();
}
