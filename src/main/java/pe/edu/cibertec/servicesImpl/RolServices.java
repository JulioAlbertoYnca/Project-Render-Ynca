package pe.edu.cibertec.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.entity.Rol;
import pe.edu.cibertec.repository.RolRepository;

@Service
public class RolServices extends ICRUDImpl<Rol, Integer>{

	@Autowired
	private RolRepository repo;
	
	@Override
	public JpaRepository<Rol, Integer> getRepository() {
		return repo;
	}

}
