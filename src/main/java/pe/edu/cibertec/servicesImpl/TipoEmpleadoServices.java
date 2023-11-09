package pe.edu.cibertec.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.entity.TipoEmpleado;
import pe.edu.cibertec.repository.TipoEmpleadoRepository;


@Service
public class TipoEmpleadoServices extends ICRUDImpl<TipoEmpleado, Integer>{

	@Autowired
	private TipoEmpleadoRepository repo;

	@Override
	public JpaRepository<TipoEmpleado, Integer> getRepository() {
		return repo;
	}
	
}
