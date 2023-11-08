package pe.edu.cibertec.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_jefe")
public class Jefe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_jefe")
	private Integer codigoJefe;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="dni")
	private String dni;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@OneToMany(mappedBy = "tbJefeA")
	@JsonIgnore
	private List<Actividad> tbActividad;
	

}
