package org.management.reservation.exception;

public class TableConflictException extends ConflictException {
    public TableConflictException(String message) {
        super(message);
    }
}