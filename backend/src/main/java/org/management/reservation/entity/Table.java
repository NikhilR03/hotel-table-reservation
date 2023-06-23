package org.management.reservation.entity;

import lombok.Data;
import lombok.Generated;
import org.management.reservation.common.TableStatus;

@Data
public class Table {
    private Long id;
    private TableStatus status;
    private String name;
    private int capacity;
}


