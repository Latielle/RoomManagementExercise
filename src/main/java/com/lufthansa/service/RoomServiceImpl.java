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

import com.lufthansa.model.Room;
import com.lufthansa.repository.RoomRepository;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomRepository roomRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public Room getRoomById(long id) {
		return roomRepository.findById(id).get();
	}

	@Override
	public List<Room> searchRooms(String building, Integer roomNumber, Integer minSeats, Integer maxSeats,
			Boolean projectorPresent) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Room> cr = cb.createQuery(Room.class);
		Root<Room> root = cr.from(Room.class);
		List<Predicate> predicates = new ArrayList<>();
		cr.select(root);
		if (building != null)
			predicates.add(cb.like(root.get("building"), building));
		if (roomNumber != null)
			predicates.add(cb.like(root.get("roomNumber").as(String.class), "%" + roomNumber + "%"));
		if (minSeats != null)
			predicates.add(cb.ge(root.get("seats"), minSeats));
		if (maxSeats != null)
			predicates.add(cb.le(root.get("seats"), maxSeats));
		if (projectorPresent != null)
			if (projectorPresent)
				predicates.add(cb.isTrue(root.get("projectorPresent")));
			else if (!projectorPresent)
				predicates.add(cb.isFalse(root.get("projectorPresent")));
		cr.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(cr).getResultList();
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
