package com.lufthansa.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lufthansa.model.Room;
import com.lufthansa.service.RoomService;

@Controller
@RequestMapping(value = "/api")
public class RoomController {

	@Autowired
	RoomService roomService;

	/**
	 * List Rooms & Search Rooms by parameters
	 * 
	 * @param parameters
	 *            Parameters for Query
	 * @return List of Rooms
	 */
	@GetMapping("/rooms")
	public ModelAndView list(@RequestParam Map<String, String> parameters) {
		ModelAndView model = new ModelAndView("room_list");
		String building = parameters.get("building");
		Integer roomNumber = getInteger(parameters.get("roomNumber"));
		Integer minSeats = getInteger(parameters.get("minSeats"));
		Integer maxSeats = getInteger(parameters.get("maxSeats"));
		Boolean projectorPresent = getBoolean(parameters.get("projectorPresent"));
		List<Room> roomList = roomService.searchRooms(building, roomNumber, minSeats, maxSeats, projectorPresent);
		model.addObject("roomList", roomList);

		return model;
	}

	public Integer getInteger(String text) {
		if (text != null)
			return Integer.parseInt(text);
		return null;
	}

	public Boolean getBoolean(String text) {
		if (text != null)
			return Boolean.parseBoolean(text);
		return null;
	}

	/**
	 * Add Room
	 * 
	 * @return Adding Room
	 */
	@GetMapping("/addRoom/")
	public ModelAndView addRoom() {
		ModelAndView model = new ModelAndView();

		Room room = new Room();
		model.addObject("roomForm", room);
		model.setViewName("room_form");

		return model;
	}

	/**
	 * Show Room
	 * 
	 * @param id
	 *            Room ID
	 * @return Room
	 */
	@GetMapping("/rooms/{id}")
	public ModelAndView editRoom(@PathVariable long id) {
		ModelAndView model = new ModelAndView();

		Room room = roomService.getRoomById(id);
		model.addObject("roomForm", room);
		model.setViewName("room_form");

		return model;
	}

	/**
	 * Create Room
	 * 
	 * @param room
	 *            Room, BindingResult bindingResult
	 * @return void
	 */
	@PostMapping("/rooms")
	public ModelAndView save(@Valid @ModelAttribute("roomForm") Room room, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView();
			model.addObject("roomForm", room);
			model.setViewName("room_form");
			return model;
		}
		try {
			roomService.saveOrUpdate(room);
		} catch (DataIntegrityViolationException ex) {
			ModelAndView model = new ModelAndView();
			model.addObject("error", true);
			model.setViewName("room_form");
			return model;
		}

		return new ModelAndView("redirect:/api/rooms");
	}

	/**
	 * Delete Room
	 * 
	 * @param id
	 *            Room ID
	 * @return void
	 */
	@DeleteMapping("/rooms/{id}")
	public ModelAndView delete(@PathVariable("id") long id) {
		roomService.deleteRoom(id);

		return new ModelAndView("redirect:/api/rooms");
	}
}