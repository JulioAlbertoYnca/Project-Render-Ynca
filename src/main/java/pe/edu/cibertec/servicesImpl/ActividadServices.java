package pe.edu.cibertec.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.edu.cibertec.entity.Actividad;
import pe.edu.cibertec.entity.Actividad_Has_Empleado;
import pe.edu.cibertec.entity.Actividad_Has_Empleado_PK;
import pe.edu.cibertec.repository.ActividadHasEmpleadoRepository;
import pe.edu.cibertec.repository.ActividadRepository;

@Service
public class ActividadServices extends ICRUDImpl<Actividad, Integer>{
	@Autowired
	private ActividadRepository repo;

	@Autowired
	private ActividadHasEmpleadoRepository actRepo;
	
	@Override
	public JpaRepository<Actividad, Integer> getRepository() {
		return repo;
	}
	
	@Transactional
	public void registrarActividad (Actividad bean) {
		try {
			repo.save(bean);
			
			for(Actividad_Has_Empleado ahe:bean.getTbActividadE()) {
				Actividad_Has_Empleado_PK pk = new Actividad_Has_Empleado_PK();
				pk.setCodigoEmpleado(ahe.getTbEmpleadoA().getCodigoEmpleado());
				pk.setIdActividad(bean.getIdActividad());
				ahe.setId(pk);
				actRepo.save(ahe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
