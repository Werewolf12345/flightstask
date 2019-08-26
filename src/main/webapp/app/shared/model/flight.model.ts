export interface IFlight {
  id?: number;
  flight?: string;
  departure?: string;
}

export const defaultValue: Readonly<IFlight> = {};
