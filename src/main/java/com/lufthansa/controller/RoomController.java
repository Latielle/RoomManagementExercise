package com.lufthansa.controller;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lufthansa.model.Room;
import com.lufthansa.service.RoomService;

@Controller
@RequestMapping(value="/api")
public class RoomController {

 @Autowired
 RoomService roomService;
 
 /**
  * List Rooms
  * @param void 
  * @return List of Rooms
  */
 @RequestMapping(value="/rooms", method=RequestMethod.GET)
 public ModelAndView list() {
  ModelAndView model = new ModelAndView("room_list");
  List<Room> roomList = roomService.getAllRooms();
  model.addObject("roomList", roomList);
  
  return model;
 }
 
 /**
  * Add Room
  * @param void 
  * @return Adding Room
  */
 @RequestMapping(value="/addRoom/", method=RequestMethod.GET)
 public ModelAndView addRoom() {
  ModelAndView model = new ModelAndView();
  
  Room room = new Room();
  model.addObject("roomForm", room);
  model.setViewName("room_form");
  
  return model;
 }
 
 /**
  * Show Room
  * @param id Room ID 
  * @return Room
  */
 @RequestMapping(value="/rooms/{id}", method=RequestMethod.GET)
 public ModelAndView editRoom(@PathVariable long id) {
  ModelAndView model = new ModelAndView();
  
  Room room = roomService.getRoomById(id);
  model.addObject("roomForm", room);
  model.setViewName("room_form");
  
  return model;
 }
 
 /**
  * Create Room
  * @param room Room,  BindingResult bindingResult
  * @return void
  */
 @RequestMapping(value="/rooms", method=RequestMethod.POST)
 public ModelAndView save(@Valid @ModelAttribute("roomForm") Room room, BindingResult bindingResult) {
     if (bindingResult.hasErrors()) {
    	 ModelAndView model = new ModelAndView();
    	  model.addObject("roomForm", room);
    	  model.setViewName("room_form");
         return model;
     }
     try {
    	 roomService.saveOrUpdate(room);
     }
	 catch (DataIntegrityViolationException ex)
	 {
		 ModelAndView model = new ModelAndView();
		 model.addObject("error", true);
   	  	 model.setViewName("room_form");
		 return model;
	 }

  return new ModelAndView("redirect:/api/rooms");
 }

 /**
  * Delete Room
  * @param id Room ID 
  * @return void
  */
 @RequestMapping(value="/deleteRoom/{id}", method=RequestMethod.GET)
 public ModelAndView delete(@PathVariable("id") long id) {
  roomService.deleteRoom(id);
  
  return new ModelAndView("redirect:/api/rooms");
 }
}