package com.demo.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.ecom.entity.UserEntity;
import com.demo.ecom.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUserName(userName);
		if (userEntity != null) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getUserType());
			List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
			grantedAuthorities.add(authority);
			return new User(userEntity.getUserName(), userEntity.getPassword(), grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
	}
}