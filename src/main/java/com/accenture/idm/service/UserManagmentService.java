package com.accenture.idm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.accenture.idm.exception.UnAuthorisedException;
import com.accenture.idm.model.User;
import com.accenture.idm.repository.UserRepository;

@Service
public class UserManagmentService {

	private static final Logger LOGGER = Logger.getLogger(UserManagmentService.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@PostMapping("/create")
	public User createUser(User inputUser) {

		List<String> role = new ArrayList<>();
		role.add("ROLE_USER");
		inputUser.setRole(role);
		User user = userRepository.save(inputUser);
		return user;
	}

	@GetMapping("/{username}")
	public User retrieveUser(String username) {
		User user = userRepository.findByUserName(username);
		return user;
	}

	@PutMapping("/update")
	public User updateUser(User inputUser) {
		User user = userRepository.findByUserName(inputUser.getUserName());
		user.setName(inputUser.getName());
		user.setPassword(inputUser.getPassword());
		user.setEmail(inputUser.getEmail());
		user = userRepository.save(user);

		return user;
	}

	public User addUserPermissions(String username, List<String> roles) {
		User user = userRepository.findByUserName(username);

		for (String role : roles) {
			if (!user.getRole().contains(role))
				user.getRole().add(role);
		}

		user = userRepository.save(user);
		return user;
	}

	public User removeUserPermissions(String username, List<String> roles) {
		User user = userRepository.findByUserName(username);

		for (String role : roles) {
			if (user.getRole().contains(role))
				user.getRole().remove(role);
		}

		user = userRepository.save(user);
		return user;
	}

	public void deleteUser(@PathVariable String username) {
		LOGGER.info("delete as proceed");
		User user = userRepository.findByUserName(username);
		userRepository.delete(user);
	}

	@GetMapping("/login")
	public User getLoginUser(String username, String password, HttpServletRequest request) {

		User user = userRepository.findByUserNameAndPassword(username, password);

		if (user == null)
			throw new UnAuthorisedException("Login failed for " + username);

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

		return user;
	}

}
