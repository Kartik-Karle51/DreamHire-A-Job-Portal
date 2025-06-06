package com.Main.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Main.jwt.JwtAuthentication;
import com.Main.jwt.JwtAuthenticationEntryPoint;

@Configuration

public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint point;
	
	@Autowired
	private JwtAuthentication filter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((req)->req.requestMatchers("/**").permitAll().anyRequest().authenticated());
//		http.csrf(csrf->csrf.disable());
//		return http.build(); 
		
		http.csrf(csrf->csrf.disable())
		.cors(cors -> {})
		.authorizeRequests()
//		.requestMatchers("/auth/login","/users/register","/users/verifyOtp/**","/users/sendOtp/**").permitAll()
//		.requestMatchers()
		.anyRequest().permitAll()
//		.authenticated()
		.and()
		.exceptionHandling(ex->ex.authenticationEntryPoint(point))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	
}
