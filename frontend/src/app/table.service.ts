import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Table, TableStats,TableRequest } from './models/table.model';

@Injectable({
  providedIn: 'root'
})
export class TableService {
  private baseUrl = 'https://restaurant1.wonderfulplant-d36ccb66.eastus.azurecontainerapps.io/api/tables';

  constructor(private http: HttpClient) { }

  getAllTables(): Observable<Table[]> {
    return this.http.get<Table[]>(this.baseUrl);
  }

  getTableById(id: number): Observable<Table> {
    return this.http.get<Table>(`${this.baseUrl}/${id}`);
  }

  createTable(request: TableRequest): Observable<Table> {
    return this.http.post<Table>(`${this.baseUrl}`,request);
  }

  getTablesByCapacity(capacity: number): Observable<Table[]> {
    return this.http.get<Table[]>(`${this.baseUrl}/capacity?capacity=${capacity}`);
  }

  updateTableStatus(id: number, status: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/${id}/status`, status);
  }

  getTableStats(): Observable<TableStats> {
    return this.http.get<TableStats>(`${this.baseUrl}/stats`);
  }
}
