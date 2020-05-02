package com.epam.courses.warehouse.model.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.epam.courses.warehouse.model.constants.ProductRecordConstants.MINIMAL_DATE;

/**
 * Date interval for search filter.
 */
public final class ProductRecordDateInterval {

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
     * @param start start interval of registration date.
     * @param end end interval of registration date.
     */
    public ProductRecordDateInterval(final Date start, final Date end) {
        if (start == null) {
            this.startInterval = null;
        } else {
            this.startInterval = new Date(start.getTime());
        }
        if (end == null) {
            this.endInterval = null;
        } else {
            this.endInterval = new Date(end.getTime());
        }
    }

    /**
     * Constructor with default date interval parameters.
     * @throws ParseException Parse exception.
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
        if (this.startInterval == null) {
            return null;
        }
        return new Date(startInterval.getTime());
    }

    /**
     * Sets the start interval of for search filter to the
     * <code>startInterval</code>.
     *
     * @param start the new start interval for search filter.
     */
    public void setStartInterval(final Date start) {
        if (start == null) {
            this.startInterval = null;
        } else {
            this.startInterval = new Date(start.getTime());
        }
    }

    /**
     * @return Returns the end interval for search filter.
     */
    public Date getEndInterval() {
        if (this.endInterval == null) {
            return null;
        }
        return new Date(endInterval.getTime());
    }

    /**
     * Sets the end interval for search filter to the <code>endInterval</code>.
     *
     * @param end the new end interval for search filter.
     */
    public void setEndInterval(final Date end) {
        if (end == null) {
            this.endInterval = null;
        } else {
            this.endInterval = new Date(end.getTime());
        }
    }

    /**
     * Override toString method.
     *
     * @return string which describes the ProductRecordDateInterval.
     */
    @Override
    public String toString() {
        return "RentalOrderDateInterval{"
                + "regStartInterval=" + startInterval
                + ", regEndInterval=" + endInterval
                + '}';
    }

}
