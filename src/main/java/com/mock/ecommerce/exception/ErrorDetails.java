package com.mock.ecommerce.exception;
import java.util.Date;
import java.util.List;

public class ErrorDetails {
    private Date timestamp;
    private String name;
    private String message;
    private List<String> details;
    private String path;

    public ErrorDetails(Date timestamp, String name, String message, List<String> details, String path) {
        super();
        this.timestamp = timestamp;
        this.name = name;
        this.message = message;
        this.details = details;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }

    public String getPath() {
        return path;
    }
}

