package com.utamatisi.app.models.milestone;

import java.sql.Timestamp;

/**
 * Created by titus.chirchir12
 * Date Created 1/24/2016.
 * Package: ${PACKAGE}
 */
public interface BusinessDateMilestoned {

    Timestamp getBusinessDateFrom();
    Timestamp getBusinessDateTo();
    void setBusinessDateTo(Timestamp timestamp);
}
