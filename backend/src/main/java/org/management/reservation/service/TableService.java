package org.management.reservation.service;

import org.management.reservation.common.TableStatus;
import org.management.reservation.dto.Stats;
import org.management.reservation.dto.TableStats;
import org.management.reservation.entity.Table;
import org.management.reservation.exception.NotFoundException;
import org.management.reservation.exception.TableConflictException;
import org.management.reservation.exception.TableNotFoundException;
import org.management.reservation.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TableService {

    @Autowired
    TableRepository tableRepo;

    public void addTable(Table table) {
        if (isExists(table.getId())) {
            throw new TableConflictException("A table is already exists with given id: '" + table.getId() + "'");
        } else {
            tableRepo.save(table);
        }
    }

    public List<Table> getAll() {
        List<Table> list = tableRepo.findAll();
        list.sort(Comparator.comparingLong(Table::getId));

        return list;
    }

    public List<Table> getByCapacity(int capacity) {
        return tableRepo.findByCapacity(capacity);
    }

    public Table getById(long id) throws NotFoundException {
        if(isExists(id)) {
            return tableRepo.findById(id);
        }
        else throw new NotFoundException("No such table with given ID");
    }

    public void updateTableStatus(TableStatus status, long tableId) throws NotFoundException {
        if(isExists(tableId)) {
            tableRepo.updateTableStatus(tableId, status);
        }
        else throw new NotFoundException("No such table with given ID");
    }

    public void deleteTable(long id) throws TableNotFoundException {
        if(isExists(id)) {
            tableRepo.deleteById(id);
        }
        else {
            throw new TableNotFoundException("No such table with given ID: "+id);
        }
    }

    public List<Table> getAllByStatus(TableStatus status) {
        return tableRepo.findAllByStatus(status);
    }

    public int countByStatus(TableStatus status) {
        return tableRepo.countByStatus(status);
    }

    public Stats getStats() {
        TableStats tableStats = new TableStats();

        tableStats.setTotalCount(tableRepo.count());
        tableStats.setFullCount(countByStatus(TableStatus.FULL));
        tableStats.setAvailableCount(countByStatus(TableStatus.AVAILABLE));
        tableStats.setOutOfServiceCount(countByStatus(TableStatus.OUT_OF_SERVICE));

        return tableStats;
    }

    public boolean isExists(long id) {
        if(tableRepo.existsById(id)) {
            return true;
        }
        else return false;
    }
}