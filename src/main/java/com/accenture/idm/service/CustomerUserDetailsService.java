package com.accenture.idm.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.accenture.idm.model.CustomerUserDetails;
import com.accenture.idm.model.User;
import com.accenture.idm.repository.UserRepository;

public class CustomerUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = Logger.getLogger(CustomerUserDetailsService.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String inputusername) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(inputusername);
		LOGGER.info(user.toString());
		if (user != null) {
			String username = user.getUserName();
			String password = user.getPassword();
			List<String> roles = user.getRole();
			CustomerUserDetails customerUserDetails = new CustomerUserDetails(username, password,
					roles.toArray(new String[roles.size()]));
			return customerUserDetails;
		}
		return null;
	}

}
