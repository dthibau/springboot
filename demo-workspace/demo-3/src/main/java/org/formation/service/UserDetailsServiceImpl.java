package org.formation.service;

import java.util.HashSet;
import java.util.Set;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Profile("domain")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		if ( email.equals("admin") ) {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new User("admin", "{noop}admin", grantedAuthorities);
		} else {
		
			Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			
			return new User(member.getEmail(), "{noop}" + member.getPassword(),
					grantedAuthorities);
		}
	}

}
