package com.utamatisi.app.models.milestone;

import java.sql.Timestamp;

/**
 * Created by titus.chirchir12
 * Date Created 1/25/2016.
 * Package: ${PACKAGE}
 */
public interface MilestonedObject {
    void closeAsOf(Timestamp timestamp);

    void delete();
}
