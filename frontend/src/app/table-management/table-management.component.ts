import { Component, OnInit } from '@angular/core';
import { TableService } from '../table.service';
import { Table, TableStats, TableRequest } from '../models/table.model'

export class TableManagementModule { }

@Component({
  selector: 'app-table-management',
  templateUrl: './table-management.component.html',
  styleUrls: ['./table-management.component.css']
})
export class TableManagementComponent implements OnInit {
  tables: Table[] = [];
  tableStats: TableStats | undefined;
  newTable: TableRequest = {  name: '', capacity: 0 };
  capacityFilter: number;

  constructor(private tableService: TableService) {
    this.capacityFilter=0
   }

  ngOnInit() {
    this.getAllTables();
    this.getTableStats();
  }

  getAllTables() {
    this.tableService.getAllTables().subscribe(
      tables => this.tables = tables,
      error => console.error('Error fetching tables:', error)
    );
  }

  getTableStats() {
    this.tableService.getTableStats().subscribe(
      stats => this.tableStats = stats,
      error => console.error('Error fetching table stats:', error)
    );
  }

  updateTableStatus(table: Table, status: string) {
    this.tableService.updateTableStatus(table.id, status).subscribe(
      () => {
        console.log('Table status updated successfully.');
        this.getAllTables(); // Refresh the table list
        this.getTableStats(); // Refresh the table stats
      },
      error => console.error('Error updating table status:', error)
    );
  }

  createTable() {
    this.tableService.createTable(this.newTable).subscribe(
      response => {
        console.log('Table created successfully:', response);
        // Clear the form inputs
        this.newTable.name = '';
        this.newTable.capacity = 0;
        // Refresh the table list
        this.getAllTables();
      },
      error => {
        console.error('Error creating table:', error);
      }
    );
  }
  getTablesByCapacity() {
    if (this.capacityFilter) {
      this.tableService.getTablesByCapacity(this.capacityFilter).subscribe(
        tables => this.tables = tables,
        error => console.error('Error fetching tables by capacity:', error)
      );
    } else {
      this.getAllTables();
    }
  }
}
