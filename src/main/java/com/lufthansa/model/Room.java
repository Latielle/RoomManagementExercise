package com.lufthansa.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="room",uniqueConstraints={@UniqueConstraint(columnNames = {"roomNumber" , "building"})})
public class Room {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	 
	 @Size (min=1,max=1, message = "Only one character allowed  ")
	 @Column(name="building")
	 private String building;
	 
	 @Range(min=1, max=999, message = "Room number must between 1 and 999")
	 @Column(name="roomNumber")
	 private int roomNumber;
	 
	 @Range(min=1, max=9999, message = "Seats must between 1 and 9999")
	 @Column(name="seats")
	 private int seats;
	 
	 @Column(name="projectorPresent")
	 private boolean projectorPresent;
	 
	 public long getId() {
		  return id;
		 }

	 public void setId(long id) {
		  this.id = id;
	 }
	
	 public String getBuilding() {
		  return building;
		 }

	 public void setBuilding(String building) {
		  this.building = building;
		 }
	 
	 public int getRoomNumber() {
		  return roomNumber;
		 }

	 public void setRoomNumber(int roomNumber) {
		  this.roomNumber = roomNumber;
		 }
	 
	 public int getSeats() {
		  return seats;
		 }

	 public void setSeats(int seats) {
		  this.seats = seats;
		 }
	 
	 public boolean getProjectorPresent() {
		  return projectorPresent;
		 }

	 public void setProjectorPresent(boolean projectorPresent) {
		  this.projectorPresent = projectorPresent;
		 }
}
