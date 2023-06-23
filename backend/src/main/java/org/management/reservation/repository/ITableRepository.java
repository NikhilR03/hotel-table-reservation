package org.management.reservation.repository;

import org.management.reservation.common.TableStatus;
import org.management.reservation.entity.Table;

import java.util.List;

public interface ITableRepository {
    Table save(Table table);
    Table findById(Long id);
    List<Table> findAll();
    void deleteById(Long id);
    List<Table> findByCapacity(int capacity);
    void updateTableStatus(Long id, TableStatus status);
    int countAllByStatus(TableStatus status);
    List<Table> findAllByStatus(TableStatus status);
    boolean existsById(Long id);
    int count();

    int countByStatus(TableStatus status);
}
