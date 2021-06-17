package com.demo.ecom.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecom.dto.AuthTokenResponse;
import com.demo.ecom.dto.EcomAuthRequest;
import com.demo.ecom.dto.UserDTO;
import com.demo.ecom.service.EcomService;
import com.demo.ecom.service.JwtUserDetailsService;
import com.demo.ecom.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class EcomAuthenticationController {

	@Autowired
	private EcomService ecomService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody EcomAuthRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthTokenResponse(token));
	}

	private void authenticate(String userName, String password) throws Exception {
		UserDTO userDTO = ecomService.getUser(userName, password);
		if(userDTO == null) {
			throw new Exception("INVALID_CREDENTIALS");
		}
	}
}
