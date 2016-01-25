package com.utamatisi.app.models.milestone;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by titus.chirchir12
 * Date Created 1/25/2016.
 * Package: ${PACKAGE}
 */
public class MilestonedObjectImpl<B extends BusinessDateMilestoned, P extends ProcessingDateMilestoned> implements MilestonedObject {
    @Override
    public void closeAsOf(Timestamp timestamp) {
        ((P)this).setProcessingDateTo(timestamp);
    }

    @Override
    public void delete() {
        ((B)this).setBusinessDateTo(new Timestamp(new Date().getTime()));
    }

}
