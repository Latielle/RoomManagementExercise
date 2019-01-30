package com.lufthansa.repository;

import org.springframework.data.repository.CrudRepository;

import com.lufthansa.model.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

}
