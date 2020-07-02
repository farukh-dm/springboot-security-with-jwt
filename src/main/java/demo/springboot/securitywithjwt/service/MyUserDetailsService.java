package demo.springboot.securitywithjwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import demo.springboot.securitywithjwt.User.MyAuthorityDto;
import demo.springboot.securitywithjwt.User.MyUserDto;

@Component(value="myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = null;
		MyUserDto myUserDto = null;
		
		// TODO: Do your validation. Make call to your DB to validate user
		// For now we're checking for user OR admin
		if(username.equals("user") || username.equals("admin")) {
			
			//Below items are something, you'll fetch using DB
			
			String password = "pass";
			ArrayList<MyAuthorityDto> authorities = new ArrayList<MyAuthorityDto>(1);
			authorities.add(new MyAuthorityDto(username.equals("user") ? "USER" : "ADMIN"));
			
			user = new User(username, password, authorities);
			myUserDto = new MyUserDto(user, "CustomAttribute1Value");
			
		} else {
			
			throw new UsernameNotFoundException("Invalid username : " + username);
			
		}
		
		return myUserDto;
	
	}

}
