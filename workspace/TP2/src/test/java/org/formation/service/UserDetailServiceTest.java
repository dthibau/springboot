package org.formation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@EnableWebSecurity
@ActiveProfiles("repositorySecurity")
public class UserDetailServiceTest {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@MockBean
	private MemberRepository mockRepository;
	
	@Test
	public void goodLogin() {
		given(mockRepository.findByEmail("dthibau@wmmod.com")).willReturn(Optional.of(new Member().email("dthibau@wmmod.com").password("secret")));
		assertThat(userDetailsService.loadUserByUsername("dthibau@wmmod.com") != null);
	}
}
