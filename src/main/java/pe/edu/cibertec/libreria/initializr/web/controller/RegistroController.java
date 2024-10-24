package pe.edu.cibertec.libreria.initializr.web.controller;

import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.libreria.initializr.web.model.ControladorUsuariosApp;
import pe.edu.cibertec.libreria.initializr.web.model.ControladorUsuariosRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class RegistroController {
	
	@Autowired
	private ControladorUsuariosRepositorio contUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(path = "/registro", consumes = "application/json")
	public ControladorUsuariosApp creaUsuariosApp(@RequestBody ControladorUsuariosApp user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return contUserRepository.save(user);
		
		
	
		
	}
	
	
}
