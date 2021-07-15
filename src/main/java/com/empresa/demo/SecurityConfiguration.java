package com.empresa.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration //Para la clase configuracion
@EnableWebSecurity //Para habilitar la seguridad web
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//Para añadir el userDetailsService
	@Autowired
	@Qualifier("userService")
	private UserDetailsService userService;
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/ver_detalle/**","/comprobante/**","/vendor/**","/img/**","/image/**","/css/**", "/imgs/**","/js/**","/").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
		.usernameParameter("username").passwordParameter("password")
		.defaultSuccessUrl("/loginsuccess").permitAll()
		.and()
		//paths que se utilizan para logout y a donde redirecciona 
		.logout().logoutUrl("/logout").logoutSuccessUrl("/")
		.permitAll().and()
		.csrf().disable();;

	}

}
