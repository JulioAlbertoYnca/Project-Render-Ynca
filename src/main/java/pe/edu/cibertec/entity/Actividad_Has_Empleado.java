package pe.edu.cibertec.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tb_actividad_empleado")
public class Actividad_Has_Empleado {

	
	@EmbeddedId
	private Actividad_Has_Empleado_PK id;
	
	@ManyToOne
	@JoinColumn(name="id_actividad", updatable = false , insertable = false , referencedColumnName = "id_actividad")
	private Actividad tbActividadE;
	
	@ManyToOne
	@JoinColumn(name="id_empleado", updatable = false , insertable = false , referencedColumnName = "id_empleado")
	private Empleado tbEmpleadoA;
	
	
}
