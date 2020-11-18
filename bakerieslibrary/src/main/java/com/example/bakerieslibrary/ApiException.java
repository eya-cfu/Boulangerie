package com.example.bakerieslibrary;

/**
 * BoulangerieApi3
 * The Boulangerie finally debugged for use, hosting service might need to be adapted
 * <p>
 * <p>
 * Contact: leafyteam@notaRealCompany.com
 */

public class ApiException extends Exception {

    private int code = 0;
    private String message = null;

    public ApiException() {
    }

    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Get the HTTP status code.
     *
     * @return HTTP status code
     */
    public int getCode() {
        return code;
    }

    /**
     * Set the HTTP status code.
     *
     * @param code HTTP status code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Get the error message.
     *
     * @return Error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the error messages.
     *
     * @param message Error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }


}
