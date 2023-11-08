package pe.edu.cibertec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import pe.edu.cibertec.security.Security;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/validar/**","/resources/**").permitAll().
		and().authorizeHttpRequests().requestMatchers("/usuario/**","/actividad/**").authenticated().
		and().formLogin().loginPage("/validar/usuario").
		defaultSuccessUrl("/validar/intranet");
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new Security();
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService());
		dao.setPasswordEncoder(password());
		return dao;
	}
	@Bean
	public BCryptPasswordEncoder password() {
		return new BCryptPasswordEncoder();
	}

}
