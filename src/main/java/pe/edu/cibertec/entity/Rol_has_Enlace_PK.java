package pe.edu.cibertec.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Rol_has_Enlace_PK implements Serializable{
	
	@Column(name="idrol")
	private Integer idRol;
	
	@Column(name="idenlace")
	private Integer idEnlace;

		

}
