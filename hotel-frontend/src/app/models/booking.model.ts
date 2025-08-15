export interface Booking {
  id: number;
  customerName: string;
  contact: string;
  roomType: 'AC' | 'Non-AC' | string;
  count: number;
  bookedRooms: number[];
  timestamp: string;
}

export interface AvailabilityInfo {
  type: 'AC' | 'Non-AC' | string;
  total: number;
  available: number;
}


