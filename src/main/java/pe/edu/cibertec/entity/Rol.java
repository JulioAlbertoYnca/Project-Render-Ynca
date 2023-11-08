package pe.edu.cibertec.entity;

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
@Table(name="tb_rol")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idrol")
	private Integer idRol;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@OneToMany(mappedBy = "tbRol")
	@JsonIgnore
	private List<Usuario> tbUsuario;

	@OneToMany(mappedBy = "tbRolE")
	@JsonIgnore
	private List<Rol_has_Enlace> tbRolE;

	
	
	
}
