package com.Database;

import java.sql.Date;
import java.time.LocalDate;

public interface IDateConversion {
    Date convertLocalDateToSQLDate(LocalDate localDate);
    LocalDate convertSQLDateToLocalDate(Date dbDate);
}
