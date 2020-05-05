package com.epam.courses.warehouse.rest.exception;

import java.util.Collections;
import java.util.List;

/**
 * Error response class. Used to send error data to the user.
 */
public final class ErrorResponse {

    /**
     * Constructor for ErrorResponse.
     */
    public ErrorResponse() {
        super();
    }

    /**
     * Constructor for ErrorResponse.
     * @param msg error message.
     * @param detailsList error details.
     */
    public ErrorResponse(final String msg, final List<String> detailsList) {
        super();
        this.message = msg;
        this.details = detailsList;
    }

    /**
     * Error message.
     */
    private String message;
    /**
     * Error details.
     */
    private List<String> details;

    /**
     * Constructor for ErrorResponse.
     * @param msg error messege.
     * @param ex Exception.
     */
    public ErrorResponse(final String msg, final Exception ex) {
        super();
        this.message = msg;
        if (ex != null) {
            this.details = Collections.singletonList(ex.getMessage());
        }
    }

    /**
     * Get message.
     * @return String message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set message.
     * @param msg message.
     */
    public void setMessage(final String msg) {
        this.message = msg;
    }

    /**
     * Get details.
     * @return <code>String</code> list.
     */
    public List<String> getDetails() {
        return details;
    }

    /**
     * Set details.
     * @param detailsList <code>String</code> list.
     */
    public void setDetails(final List<String> detailsList) {
        this.details = detailsList;
    }
}
