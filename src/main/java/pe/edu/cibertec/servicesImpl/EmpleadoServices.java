package pe.edu.cibertec.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.entity.Empleado;
import pe.edu.cibertec.repository.EmpleadoRepository;
@Service
public class EmpleadoServices extends ICRUDImpl<Empleado, Integer>{
	
	@Autowired
	private EmpleadoRepository repo;

	@Override
	public JpaRepository<Empleado, Integer> getRepository() {
		return repo;
	}
	
	public List<Empleado> listaPorUserName(String usName){
		return repo.listadoEmpleadoPorUserName(usName);
	}
	
	public List<Object[]> obtenerListaActividadesEmpleado(Integer codEmpleado){
		return repo.listaActividadesEmpleado(codEmpleado);
	}
	
	
}
