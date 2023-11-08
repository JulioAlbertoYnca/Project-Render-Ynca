package pe.edu.cibertec.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.entity.Jefe;
import pe.edu.cibertec.repository.JefeRepository;
@Service
public class JefeServices extends ICRUDImpl<Jefe, Integer>{
	
	@Autowired
	private JefeRepository repo;

	@Override
	public JpaRepository<Jefe, Integer> getRepository() {
		return repo;
	}

}
