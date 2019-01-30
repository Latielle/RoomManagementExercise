package com.lufthansa.service;

import java.util.List;

import com.lufthansa.model.Room;

public interface RoomService {

	/**
	 * Getting Room by Id
	 * 
	 * @param id
	 *            Room Id
	 * @return Room
	 */
	Room getRoomById(long id);

	/**
	 * Getting list of Rooms by parameters
	 * 
	 * @param String
	 *            building, String roomNumber, String minSeats, String maxSeats,
	 *            String projectorPresent
	 * @return List<Room>
	 */
	List<Room> searchRooms(String building, Integer roomNumber, Integer minSeats, Integer maxSeats,
			Boolean projectorPresent);

	/**
	 * Create or Update Room
	 * 
	 * @param id
	 *            Room Id
	 */
	void saveOrUpdate(Room room);

	/**
	 * Delete Room
	 * 
	 * @param id
	 *            Room Id
	 */
	void deleteRoom(long id);
}
