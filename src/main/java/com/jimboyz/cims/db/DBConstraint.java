package com.jimboyz.cims.db;

import org.hibernate.exception.ConstraintViolationException;

public class DBConstraint {

    public static boolean isDuplicateConstraintViolation(Throwable e) {
        Throwable cause = e;

        while (cause != null) {
            if (cause instanceof ConstraintViolationException cve) {
                String sqlState = cve.getSQLException().getSQLState();
                int errorCode = cve.getSQLException().getErrorCode();
                String message = cve.getSQLException().getMessage();

                // Common SQL state codes for duplicate key violation:
                // PostgreSQL: 23505
                // SQL Server: 2601 or 2627 (via error code)
                // Oracle: ORA-00001 (error code = 1)
                // MySQL: error code = 1062

                return
                        "23505".equals(sqlState) ||              // PostgreSQL
                                errorCode == 1062 ||             // MySQL
                                errorCode == 1 ||                // Oracle
                                errorCode == 2601 ||             // SQL Server
                                errorCode == 2627 ||             // SQL Server
                                message != null && message.toLowerCase().contains("unique constraint");
            }

            cause = cause.getCause(); // dig deeper into wrapped exceptions
        }

        return false;
    }
}
