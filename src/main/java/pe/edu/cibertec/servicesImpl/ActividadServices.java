package pe.edu.cibertec.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.entity.Actividad;
import pe.edu.cibertec.repository.ActividadRepository;

@Service
public class ActividadServices extends ICRUDImpl<Actividad, Integer>{
	@Autowired
	private ActividadRepository repo;

	@Override
	public JpaRepository<Actividad, Integer> getRepository() {
		return repo;
	}

}
