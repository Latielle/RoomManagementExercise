package com.lufthansa.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lufthansa.model.*;
import com.lufthansa.repository.*;

@Service
@Transactional
public class RoomServiceImpl implements RoomService{

	 @Autowired
	 RoomRepository roomRepository;

	 @Override
	 public List<Room> getAllRooms() {
	  return (List<Room>) roomRepository.findAll();
	 }

	 @Override
	 public Room getRoomById(long id) {
	  return roomRepository.findById(id).get();
	 }

	 @Override
	 public void saveOrUpdate(Room room) {
			 roomRepository.save(room);
	 }

	 @Override
	 public void deleteRoom(long id) {
		 roomRepository.deleteById(id);
	 }
}
