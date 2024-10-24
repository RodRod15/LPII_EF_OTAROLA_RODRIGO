package pe.edu.cibertec.libreria.initializr.web.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ControladorUsuariosService implements UserDetailsService{

	
	@Autowired
	private ControladorUsuariosRepositorio repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<ControladorUsuariosApp> user = repository.findByUsername(username);
		if (user.isPresent()) {
			
			var userObj = user.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.build();
		} else {
			
			throw new UsernameNotFoundException(username);
			
		}
		
		
		
	}
	
	
	public UserDetails loadUserByEmail(String email) throws Exception {
		Optional<ControladorUsuariosApp> user = repository.findByEmail(email);
		if (user.isPresent()) {
			
			var userObj = user.get();
			return User.builder()
					.username(userObj.getEmail())
					.username(userObj.getPassword())
					.build();
		} else {
			
			throw new Exception();
			
		}
		
		
		
	}

}
