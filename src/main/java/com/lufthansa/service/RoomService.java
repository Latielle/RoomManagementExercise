package com.lufthansa.service;
import java.util.List;

import com.lufthansa.model.*;

public interface RoomService {
	 public List<Room> getAllRooms();
	 
	 public Room getRoomById(long id);
	 
	 public void saveOrUpdate(Room room);
	 
	 public void deleteRoom(long id);
}
