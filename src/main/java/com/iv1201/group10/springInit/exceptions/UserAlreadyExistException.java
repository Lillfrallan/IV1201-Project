package com.iv1201.group10.springInit.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Thrown during the registration process whenever the userdetails email, personal number or username
 * are not unique.
 */
public class UserAlreadyExistException extends AuthenticationException {
    private final String fieldName;

    /**
     * Creates a new instance with the specified message.
     * @param message Contains the cause of the error.
     * @param fieldName Contains the field that was not unique (email, personal number or username).
     */
    public UserAlreadyExistException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    /**
     * @return The fieldName variable.
     */
    public String getFieldName() {
        return fieldName;
    }
}
