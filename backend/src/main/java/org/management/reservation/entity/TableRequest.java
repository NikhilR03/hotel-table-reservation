package org.management.reservation.entity;

import lombok.Data;

@Data
public class TableRequest {
    private String name;
    private int capacity;
}