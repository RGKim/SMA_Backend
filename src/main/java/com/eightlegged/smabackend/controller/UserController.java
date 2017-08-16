package com.eightlegged.smabackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eightlegged.smabackend.entity.Meeting;
import com.eightlegged.smabackend.entity.User;
import com.eightlegged.smabackend.service.UserService;

/**
 * @author Kim Sae-Young(heehouse1@gmail.com)
 *
 * @FileName UserController.java
 * @Project smabackend
 * @Date 2017. 8. 9.
 */

@RestController
public class UserController {

	@Autowired
	UserService userservice;
	
	@CrossOrigin
	@RequestMapping(
			value="/user/add", method=RequestMethod.POST, 
			consumes= {MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public String createUser(@RequestBody User user){
		System.out.println("NAME:" + user.getUserName());
		System.out.println("E-Mail: " + user.getEmail());
		System.out.println("Pssword: " + user.getPassword());
		return userservice.createUser(user.getUserName(), user.getEmail(),user.getPassword());
	}
	
	@CrossOrigin
	@RequestMapping(
			value="/user/all/{email}", method=RequestMethod.GET,
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<Meeting> meetingList(@PathVariable String email){
		return userservice.findByEmail(email).getMeetingList();
	}
	
	@CrossOrigin
	@RequestMapping(
			value="/user/wait/{email}", method=RequestMethod.GET,
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<Meeting> meetingList_w(@PathVariable String email){
		return userservice.meetingList_w(userservice.findByEmail(email));
	}
	
	@CrossOrigin
	@RequestMapping(
			value="/user/end/{email}", method=RequestMethod.GET,
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<Meeting> meetingList_c(@PathVariable String email){
		return userservice.meetingList_c(userservice.findByEmail(email));
	}
	
	@CrossOrigin
	@RequestMapping(
			value="/user/login",
			method=RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE},
			produces={MediaType.APPLICATION_JSON_VALUE})
	public String login(@RequestBody User user){
		System.out.println("E-Mail: " + user.getEmail());
		System.out.println("Pssword: " + user.getPassword());
		return userservice.login(user.getEmail(), user.getPassword());
	}

}
