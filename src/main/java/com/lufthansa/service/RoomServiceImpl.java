package com.lufthansa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lufthansa.model.*;
import com.lufthansa.repository.*;

@Service
@Transactional
public class RoomServiceImpl implements RoomService{

	 @Autowired
	 RoomRepository roomRepository;
	 
	 @PersistenceContext
	 private EntityManager em;

	 /**
	  * Getting Room by Id
	  * @param id Room Id
	  * @return Room
	  */
	 @Override
	 public Room getRoomById(long id) {
	  return roomRepository.findById(id).get();
	 }
	 
	 /**
	  * Getting list of Rooms by parameters
	  * @param String building, String roomNumber, String minSeats, String maxSeats, String projectorPresent
	  * @return List<Room>
	  */
	@Override
	public List<Room> searchRooms(String building, String roomNumber, String minSeats, String maxSeats, String projectorPresent) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Room> cr = cb.createQuery(Room.class);
		Root <Room> root = cr.from(Room.class);
		List<Predicate> predicates = new ArrayList<>();
		cr.select(root);
		if (building!=null)
			predicates.add(cb.like(root.get("building"), building));
		if (roomNumber!=null)
			predicates.add(cb.like(root.get("roomNumber").as(String.class), "%" + Integer.parseInt(roomNumber) + "%"));
		if (minSeats!=null)
			predicates.add(cb.ge(root.get("seats"), Integer.parseInt(minSeats)));
		if (maxSeats!=null)
			predicates.add(cb.le(root.get("seats"), Integer.parseInt(maxSeats)));
		if (projectorPresent!=null)
			if (projectorPresent.equals("true"))
				predicates.add(cb.isTrue(root.get("projectorPresent")));
			else if (projectorPresent.equals("false"))
				predicates.add(cb.isFalse(root.get("projectorPresent")));
		cr.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(cr).getResultList();
	}

	 /**
	  * Create or Update Room
	  * @param id Room Id
	  */
	 @Override
	 public void saveOrUpdate(Room room) {
			 roomRepository.save(room);
	 }

	 /**
	  * Delete Room
	  * @param id Room Id
	  */
	 @Override
	 public void deleteRoom(long id) {
		 roomRepository.deleteById(id);
	 }
}
