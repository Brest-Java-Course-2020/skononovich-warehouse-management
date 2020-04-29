package com.epam.courses.warehouse.model.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.epam.courses.warehouse.model.constants.ProductRecordConstants.MINIMAL_DATE;

public class ProductRecordDateInterval {

    /**
     * The start interval for search filter.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startInterval;

    /**
     * The end interval for search filter.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endInterval;

    /**
     * Constructor with date interval parameters.
     *
     * @param startInterval start interval of registration date.
     * @param endInterval end interval of registration date.
     */
    public ProductRecordDateInterval(Date startInterval, Date endInterval) {
        if(startInterval == null){
            this.startInterval = null;
        } else {
            this.startInterval = new Date(startInterval.getTime());
        }
        if(endInterval == null){
            this.endInterval = null;
        } else {
            this.endInterval = new Date(endInterval.getTime());
        }
    }

    /**
     * Constructor with default date interval parameters.
     */
    public ProductRecordDateInterval() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.startInterval = simpleDateFormat.parse(MINIMAL_DATE);
        this.endInterval = new Date(Calendar.getInstance().getTime().getTime());
    }

    /**
     * @return Returns the start interval for search filter.
     */
    public Date getStartInterval() {
        if(this.startInterval == null){
            return null;
        }
        return new Date(startInterval.getTime());
    }

    /**
     * Sets the start interval of for search filter to the <code>startInterval</code>.
     *
     * @param startInterval the new start interval for search filter.
     */
    public void setStartInterval(Date startInterval) {
        if(startInterval == null){
            this.startInterval = null;
        } else {
            this.startInterval = new Date(startInterval.getTime());
        }
    }

    /**
     * @return Returns the end interval for search filter.
     */
    public Date getEndInterval() {
        if(this.endInterval == null){
            return null;
        }
        return new Date(endInterval.getTime());
    }

    /**
     * Sets the end interval for search filter to the <code>endInterval</code>.
     *
     * @param endInterval the new end interval for search filter.
     */
    public void setEndInterval(Date endInterval) {
        if(endInterval == null){
            this.endInterval = null;
        } else {
            this.endInterval = new Date(endInterval.getTime());
        }
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
