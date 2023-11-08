package pe.edu.cibertec.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_actividad")
public class Actividad {
	
	@Id
	@Column(name="id_actividad")
	private Integer idActividad;
	
	@Column(name="nombre_actividad")
	private String nombreActividad;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="fecha_inicio")
	private LocalDate fechaInicio;
	
	@Column(name="fecha_fin")
	private LocalDate fechaFin;
	
	@Column(name="estado")
	private Integer estado;
		
	@ManyToOne
	@JoinColumn(name="id_jefe")
	private Jefe tbJefeA;
	
	@OneToMany(mappedBy = "tbActividadE")
	@JsonIgnore
	private List<Actividad_Has_Empleado> tbActividadE;

}
