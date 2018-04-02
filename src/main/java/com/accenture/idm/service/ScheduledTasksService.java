package com.accenture.idm.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class ScheduledTasksService {

	@Autowired
	UserManagmentService UserManagmentService;

	private static final Logger LOGGER = Logger.getLogger(ScheduledTasksService.class.getName());

	
	/*
	 * Display all users every 5 mins
	 */
	@Scheduled(fixedRate = 300000)
	public void displayAllUser() {
		LOGGER.info("" + UserManagmentService.getAllUsers());

	}
}
