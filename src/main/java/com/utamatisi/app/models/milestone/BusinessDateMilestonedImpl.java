package com.utamatisi.app.models.milestone;

import com.utamatisi.app.util.DateUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by titus.chirchir12
 * Date Created 1/25/2016.
 * Package: ${PACKAGE}
 */
@MappedSuperclass
public class BusinessDateMilestonedImpl extends MilestonedObjectImpl implements BusinessDateMilestoned {

    @Column(name = "out_z")
    private Timestamp businessDateTo;

    @Column(name = "in_z")
    private Timestamp businessDateFrom;

    public BusinessDateMilestonedImpl() {
        this.businessDateFrom = new Timestamp(new Date().getTime());
        this.businessDateTo = DateUtil.getInfinityTimestamp();
    }

    @Override
    public Timestamp getBusinessDateFrom() {
        return businessDateFrom;
    }

    @Override
    public Timestamp getBusinessDateTo() {
        return this.businessDateTo;
    }

    public void setBusinessDateTo(Timestamp businessDateTo) {
        this.businessDateTo = businessDateTo;
    }
    public void setBusinessDateFrom(Timestamp businessDateFrom) {
        this.businessDateFrom = businessDateFrom;
    }
}
