package pe.edu.cibertec.libreria.initializr.web.model;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ControladorUsuariosRepositorio extends JpaRepository<ControladorUsuariosApp, Long> {

	Optional<ControladorUsuariosApp> findByUsername(String username);
	
	Optional<ControladorUsuariosApp> findByEmail(String email);
	
}
