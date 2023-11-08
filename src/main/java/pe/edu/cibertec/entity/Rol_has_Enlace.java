package pe.edu.cibertec.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tb_rol_enlace")
public class Rol_has_Enlace {
	
	@EmbeddedId
	private Rol_has_Enlace_PK id;
	
	@ManyToOne
	@JoinColumn(name="idrol", updatable = false , insertable = false , referencedColumnName = "idrol")
	private Rol tbRolE;
	
	@ManyToOne
	@JoinColumn(name="idenlace", updatable = false , insertable = false , referencedColumnName = "idenlace")
	private Enlace tbEnlaceR;
	
}
