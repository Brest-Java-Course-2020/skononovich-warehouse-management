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
        this.startInterval = startInterval;
        this.endInterval = endInterval;
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
