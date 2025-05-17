import { EventModel } from "./Event.model";

export interface CategoryRequestDTO {
  id: number;
  name: string;
  events: EventModel[];
}
