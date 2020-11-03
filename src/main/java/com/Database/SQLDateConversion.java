package com.Database;

import java.sql.Date;
import java.time.LocalDate;

public class SQLDateConversion implements IDateConversion {

    @Override
    public Date convertLocalDateToSQLDate(LocalDate localDate) {
        if(localDate == null) {
            return null;
        } else {
            return Date.valueOf(localDate);
        }
    }

    @Override
    public LocalDate convertSQLDateToLocalDate(Date sqlDate) {
        if(sqlDate == null) {
            return null;
        } else {
            return sqlDate.toLocalDate();
        }
    }
}
