package pe.edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.cibertec.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{
	
	@Query("Select e from Empleado e where e.userName like %?1%")
	public List<Empleado> listadoEmpleadoPorUserName(String usName);
	
	@Query("SELECT ae.id.idActividad, a.nombreActividad, a.estado FROM Empleado e INNER JOIN Actividad_Has_Empleado ae ON e.codigoEmpleado = ae.tbEmpleadoA.codigoEmpleado INNER JOIN Actividad a ON ae.tbActividadE.idActividad = a.idActividad WHERE e.codigoEmpleado = ?1")
	List<Object[]> listaActividadesEmpleado(Integer codE);
		
}
