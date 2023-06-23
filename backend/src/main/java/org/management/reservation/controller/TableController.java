package org.management.reservation.controller;

import org.management.reservation.common.TableStatus;
import org.management.reservation.dto.Stats;
import org.management.reservation.dto.StringResponse;
import org.management.reservation.dto.TableStats;
import org.management.reservation.entity.Table;
import org.management.reservation.entity.TableRequest;
import org.management.reservation.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "https://victorious-water-09600150f.3.azurestaticapps.net")
@RequestMapping("/api/tables")
@RestController
public class TableController {

    @Autowired
    TableService tableService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> addTable(@RequestBody TableRequest table) {
        //if(table.getId() <= 0) return ResponseEntity.badRequest().body(new StringResponse("Given ID: "+table.getId()+" is not valid."));
        Table tableNew = new Table();
        try{
            tableNew.setStatus(TableStatus.AVAILABLE);
            tableNew.setId((long)(Math.random()*1000000));
            tableNew.setName(table.getName());
            tableNew.setCapacity(table.getCapacity());
            tableService.addTable(tableNew);
            return ResponseEntity.ok().body(new StringResponse("Table " + tableNew.getId() + " has created successfully."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new StringResponse(e.getMessage()));
        }
    }

    @DeleteMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteTable(@PathVariable long id) {
        try{
            tableService.deleteTable(id);
            return ResponseEntity.ok().body(new StringResponse("Table " + id + " has deleted successfully."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new StringResponse(e.getMessage()));
        }
    }

    @GetMapping(path = "{id}")
    @ResponseBody
    public ResponseEntity<Object> getById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(tableService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new StringResponse(e.getMessage()));
        }
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Table>> getTables() {
        return new ResponseEntity<>(tableService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/capacity")
    @ResponseBody
    public ResponseEntity<List<Table>> getByCapacity(@RequestParam("capacity") int capacity) {
        return new ResponseEntity<>(tableService.getByCapacity(capacity), HttpStatus.OK);
    }

    @GetMapping(path = "/stats")
    @ResponseBody
    public ResponseEntity<Stats> getAllStats() {
        return new ResponseEntity<>(tableService.getStats(), HttpStatus.OK);
    }

    @PostMapping(path = "{id}/status")
    @ResponseBody
    public ResponseEntity<Object> setStatus(@RequestBody String statusString, @PathVariable long id) {
        try {
            TableStatus status = TableStatus.valueOf(statusString.toUpperCase().replaceAll(" ", "_"));
            tableService.updateTableStatus(status, id);
            return ResponseEntity.ok(new StringResponse("Table status has updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new StringResponse(e.getMessage()));
        }
    }
}