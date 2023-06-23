package org.management.reservation.exception;

public class TableNotFoundException extends NotFoundException {
    public TableNotFoundException(String message) {
        super(message);
    }
}