package pe.edu.cibertec.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Actividad_Has_Empleado_PK implements Serializable{
	
	@Column(name="id_actvidad")
	private Integer idActividad;
	
	@Column(name="id_empleado")
	private Integer codigoEmpleado;

}
