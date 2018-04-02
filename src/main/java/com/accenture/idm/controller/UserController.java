package com.accenture.idm.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.idm.model.User;
import com.accenture.idm.service.UserManagmentService;

@RestController
@RequestMapping("v1/users")
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
	
	@Autowired
	UserManagmentService userManagmentService;
	
	
    /*
     * Retreive all users
     */
	@GetMapping("/")
	public List<User> getAllUsers() {
	   return (List<User>) userManagmentService.getAllUsers();
	}

	/*
	 * Create a user
	 */
	@PostMapping("/create")
	public User createUser(@RequestBody User inputUser) {
		
		User user = userManagmentService.createUser(inputUser);		
		return user;
	}
	
	/*
	 * Retrieve a user by user name
	 */

	@GetMapping("/{username}")
	public User retrieveUser(@PathVariable String username) {
		
		User user = userManagmentService.retrieveUser(username);
		
		return user;
	}
	
	/*
	 * Update a user
	 */

	@PutMapping("/update")
	public User updateUser(@RequestBody User inputUser) {
		
		User user = userManagmentService.updateUser(inputUser);
		return user;
	}
	
	/*
	 * Update user permissions. 
	 */

	@PutMapping("/add/permissions")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User addUserPermissions(@RequestParam String username, @RequestParam List<String> roles) {
		
		User user = userManagmentService.addUserPermissions(username, roles);		
		return user;
	}
	
	/*
	 * Remove user permissions
	 */

	@PutMapping("/remove/permissions")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User removeUserPermissions(@RequestParam String username, @RequestParam List<String> roles) {
		
		User user = userManagmentService.removeUserPermissions(username, roles);
		return user;
	}
	
	/*
	 * Delete a user
	 */

	@DeleteMapping("/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUser(@PathVariable String username) {
	
		userManagmentService.deleteUser(username);		
	}
	
	/*
	 * Custom login for created user.
	 */

	@GetMapping("/login")
	public User getLoginUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {

		User user = userManagmentService.getLoginUser(username, password, request);
		
		return user;
	}
}
