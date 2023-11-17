package pe.edu.cibertec.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleEmpleado  implements Serializable{
	
	private int codigo;
	private String userName, nombre, apellido;
	
	
	
}
