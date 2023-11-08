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
@Table(name="tb_enlace")
public class Enlace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idenlace")
	private Integer idEnlace;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name="ruta")
	private String ruta;

	@OneToMany(mappedBy = "tbEnlaceR")
	@JsonIgnore
	private List<Rol_has_Enlace> tbEnlaceR;


	
	

}
