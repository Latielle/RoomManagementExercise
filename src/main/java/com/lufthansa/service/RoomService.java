package com.lufthansa.service;
import java.util.List;

import com.lufthansa.model.*;

public interface RoomService {
	
	 Room getRoomById(long id);
	 
	 List<Room> searchRooms (String building, String roomNumber, String minSeats, String maxSeats, String projectorPresent);
	 
	 void saveOrUpdate(Room room);
	 
	 void deleteRoom(long id);
}
