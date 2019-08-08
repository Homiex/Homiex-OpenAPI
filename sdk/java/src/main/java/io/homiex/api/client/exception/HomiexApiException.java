package io.homiex.api.client.exception;

import io.homiex.api.client.HomiexApiError;

/**
 * An exception which can occur while invoking methods of the Homiex API.
 */
public class HomiexApiException extends RuntimeException {

    private static final long serialVersionUID = 3788669840036201041L;
    /**
     * Error response object returned by Homiex API.
     */
    private HomiexApiError error;

    /**
     * Instantiates a new Homiex api exception.
     *
     * @param error an error response object
     */
    public HomiexApiException(HomiexApiError error) {
        this.error = error;
    }

    /**
     * Instantiates a new Homiex api exception.
     */
    public HomiexApiException() {
        super();
    }

    /**
     * Instantiates a new Homiex api exception.
     *
     * @param message the message
     */
    public HomiexApiException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Homiex api exception.
     *
     * @param cause the cause
     */
    public HomiexApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Homiex api exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public HomiexApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return the response error object from Homiex API, or null if no response object was returned (e.g. server returned 500).
     */
    public HomiexApiError getError() {
        return error;
    }

    @Override
    public String getMessage() {
        if (error != null) {
            return error.getMsg();
        }
        return super.getMessage();
    }
}
