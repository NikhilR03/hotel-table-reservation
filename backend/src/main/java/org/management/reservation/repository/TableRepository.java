package org.management.reservation.repository;

import org.management.reservation.common.TableStatus;
import org.management.reservation.entity.Table;
import org.springframework.stereotype.Repository;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TableRepository implements ITableRepository {
    private final Hashtable<Long, Table> tableData = new Hashtable<>();
    private Long nextId = 1L;

    @Override
    public Table save(Table table) {
        if (table.getId() == null) {
            table.setId(nextId++);
        }
        tableData.put(table.getId(), table);
        return table;
    }

    @Override
    public Table findById(Long id) {
        return tableData.get(id);
    }

    @Override
    public List<Table> findAll() {
        return tableData.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        tableData.remove(id);
    }

    @Override
    public List<Table> findByCapacity(int capacity) {
        return tableData.values()
                .stream()
                .filter(table -> table.getCapacity() == capacity)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTableStatus(Long id, TableStatus status) {
        Table table = tableData.get(id);
        if (table != null) {
            table.setStatus(status);
            tableData.put(id, table);
        }
    }

    @Override
    public int countAllByStatus(TableStatus status) {
        return (int) tableData.values()
                .stream()
                .filter(table -> table.getStatus().equals(status))
                .count();
    }

    @Override
    public List<Table> findAllByStatus(TableStatus status) {
        return tableData.values()
                .stream()
                .filter(table -> table.getStatus().equals(status))
                .collect(Collectors.toList());
    }
    @Override
    public boolean existsById(Long id) {
        return tableData.containsKey(id);
    }
    @Override
    public int count() {
        return tableData.size();
    }
    @Override
    public int countByStatus(TableStatus status) {
        return (int) tableData.values()
                .stream()
                .filter(table -> table.getStatus().equals(status))
                .count();
    }


}
