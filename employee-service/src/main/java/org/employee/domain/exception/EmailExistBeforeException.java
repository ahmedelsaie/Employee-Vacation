package org.employee.domain.exception;

public class EmailExistBeforeException extends RuntimeException {

    public EmailExistBeforeException(String email) {
        super(createMessage(email));
    }

    private static String createMessage(String email) {
        return "Email " + email + " is Used Before";
    }
}
