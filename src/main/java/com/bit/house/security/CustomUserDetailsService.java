package com.bit.house.security;

import com.bit.house.domain.AllMemberVO;
import com.bit.house.domain.CustomUser;
import com.bit.house.mapper.AllMemberMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Setter(onMethod_ = { @Autowired })
	private AllMemberMapper allMemberMapper;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		log.warn("Load User By UserName : " + userName);

		// userName means userid
		AllMemberVO allMemberVO = allMemberMapper.read(userName);

		log.warn("queried by member mapper: " + allMemberVO);

		return allMemberVO == null ? null : new CustomUser(allMemberVO);
	} 

}
