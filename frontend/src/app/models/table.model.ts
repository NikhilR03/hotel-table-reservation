export interface Table {
  id: number;
  status: string;
  name: string;
  capacity: number;
}

export interface TableRequest {
  name: string;
  capacity: number;
}


export interface TableStats {
  totalCount: number;
  availableCount: number;
  fullCount: number;
  outOfServiceCount: number;
  availablePercent: number;
  fullPercent: number;
  outOfServicePercent: number;
}
