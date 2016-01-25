package com.utamatisi.app.util;

import java.sql.Timestamp;

/**
 * Created by titus.chirchir12
 * Date Created 1/25/2016.
 * Package: ${PACKAGE}
 */
public class DateUtil {
    public static Timestamp getInfinityTimestamp()
    {
        return Timestamp.valueOf("2099-12-31 23:59:00.0");
    }

    public static Timestamp getInfinityProcessingTimestamp() {
        return Timestamp.valueOf("9999-12-31 23:59:00.0");
    }
}
