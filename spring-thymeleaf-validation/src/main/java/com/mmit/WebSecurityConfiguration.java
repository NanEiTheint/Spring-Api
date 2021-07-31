package com.mmit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public PasswordEncoder encoder;

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		//create custom user
		UserDetails user1=User
				.withUsername("Nan Ei")
				.password(encoder.encode("123"))
				.roles("Admin")
				.build();
		
		UserDetails user2=User
				.withUsername("Khin Khin")
				.password(encoder.encode("123"))
				.roles("User")
				.build();
		return new InMemoryUserDetailsManager(user1,user2);
				
	}
	
	
	//filter http route
	//authorize user
	//define authentication mechanism (default login form,custom login form,basic http form)
	//define error
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//use default login form authentication
		/*
		http
		.authorizeRequests()
		.antMatchers("/").permitAll() //allow index page
		.antMatchers("/employee-create").hasAnyRole("User","Admin")//allow user
		.antMatchers("/employee-edit/**","/employee-delete/**").hasRole("Admin")
		.anyRequest().authenticated() //not allow others
			//define web filter above part
		.and() // next part because not mix
		.formLogin() //default form design
		.and()
		.logout().permitAll() //define logout
		.and()
		.exceptionHandling().accessDeniedPage("/403") //define error page
		;
		*/
		
		//use custom login form
		/*
		http
		.authorizeRequests()
		.antMatchers("/").permitAll() //allow index page
		.antMatchers("/employee-create").hasAnyRole("User","Admin")//allow user
		.antMatchers("/employee-edit/**","/employee-delete/**").hasRole("Admin")
		.anyRequest().authenticated() //not allow others
			//define web filter above part
		.and() // next part because not mix
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll() //define logout
		.and()
		.exceptionHandling().accessDeniedPage("/403") //define error page
		;
		*/
		
		
		//use http basic form(no cookie,no session,manualy logout(close browser)) request until correct login
		http
		.authorizeRequests()
		.antMatchers("/").permitAll() //allow index page
		.antMatchers("/employee-create").hasAnyRole("User","Admin")//allow user
		.antMatchers("/employee-edit/**","/employee-delete/**").hasRole("Admin")
		.anyRequest().authenticated() //not allow others
			//define web filter above part
		.and() // next part because not mix
		.httpBasic() //define basic authentication
		.and()
		.exceptionHandling().accessDeniedPage("/403")
		;
	}

	//allow files enter static folder
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**");
	}

	//encoder password
	@Bean //call this anywhere
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
