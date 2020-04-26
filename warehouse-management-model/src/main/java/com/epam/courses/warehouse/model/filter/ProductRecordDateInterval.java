package com.epam.courses.warehouse.model.filter;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.util.Calendar;

import static com.epam.courses.warehouse.model.constants.ProductRecordConstants.MINIMAL_DATE;

public class ProductRecordDateInterval {

    /**
     * The start interval for search filter.
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date startInterval;

    /**
     * The end interval for search filter.
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endInterval;

    /**
     * Constructor with date interval parameters.
     *
     * @param startInterval start interval of registration date.
     * @param endInterval end interval of registration date.
     */
    public ProductRecordDateInterval(String startInterval, String endInterval) {
        this.startInterval = Date.valueOf(startInterval);
        this.endInterval = Date.valueOf(endInterval);
    }

    /**
     * Constructor with default date interval parameters.
     */
    public ProductRecordDateInterval() {
        this.startInterval = Date.valueOf(MINIMAL_DATE);
        this.endInterval = new Date(Calendar.getInstance().getTime().getTime());
    }

    /**
     * @return Returns the start interval for search filter.
     */
    public Date getStartInterval() {
        return startInterval;
    }

    /**
     * Sets the start interval of for search filter to the <code>regStartInterval</code>.
     *
     * @param startInterval the new start interval for search filter.
     */
    public void setStartInterval(Date startInterval) {
        this.startInterval = startInterval;
    }

    /**
     * @return Returns the end interval for search filter.
     */
    public Date getEndInterval() {
        return endInterval;
    }

    /**
     * Sets the end interval for search filter to the <code>endInterval</code>.
     *
     * @param endInterval the new end interval for search filter.
     */
    public void setEndInterval(Date endInterval) {
        this.endInterval = endInterval;
    }

    /**
     * Override toString method.
     *
     * @return string which describes the ProductRecordDateInterval.
     */
    @Override
    public String toString() {
        return "RentalOrderDateInterval{" +
                "regStartInterval=" + startInterval +
                ", regEndInterval=" + endInterval +
                '}';
    }

}
