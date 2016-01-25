package com.utamatisi.app.models.milestone;

import java.sql.Timestamp;

/**
 * Created by titus.chirchir12
 * Date Created 1/24/2016.
 * Package: ${PACKAGE}
 */
public interface ProcessingDateMilestoned {

    Timestamp getProcessingDateFrom();
    Timestamp getProcessingDateTo();
    void setProcessingDateTo(Timestamp timestamp);
}
