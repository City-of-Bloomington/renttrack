package in.bloomington.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.bloomington.rental.dao.UserDao;
import in.bloomington.rental.model.RentUser;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

		@Autowired
		private UserDao userDao;
		
		@Transactional(readOnly = true)
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

				RentUser user = userDao.findByUsername(username);
				UserBuilder builder = null;
				if (user != null) {
						builder = org.springframework.security.core.userdetails.User.withUsername(username);
						builder.disabled(!user.isActive());
						String pass=new BCryptPasswordEncoder().encode("letmein");
						builder.password(pass);
						String role = user.getRole();
						if(role == null || role.equals("")){
								role = "view";
						}
						String [] roles = new String[1];
						roles[0] = role;
						/*
						String[] authorities = user.getAuthorities()
								.stream().map(a -> a.getAuthority()).toArray(String[]::new);
						*/
						builder.authorities(roles);
				} else {
						throw new UsernameNotFoundException("User not found.");
				}
				return builder.build();
		}		

}
