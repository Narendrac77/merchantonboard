package fss.acquisition.merchantonboard.web.rest.errors;

import java.util.Date;

public class ErrorDetails {

    private Date date;
    private String message;
    private String details;

    public ErrorDetails(Date date, String message, String details) {
        super();
        this.date = date;
        this.message = message;
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
