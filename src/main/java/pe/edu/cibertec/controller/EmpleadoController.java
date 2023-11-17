package pe.edu.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.servicesImpl.EmpleadoServices;
import pe.edu.cibertec.servicesImpl.UsuarioServices;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoServices serEmp;
	@Autowired 
	private UsuarioServices serUsu;
	
	@GetMapping("/lista")
	public String listaA(Model model) {
		Usuario buscarUsuario = serUsu.obtenerUsuario();
		if(buscarUsuario != null) {
			model.addAttribute("UsuarioActual", buscarUsuario);
			
			List<Object[]> actividadesAsignadas = serEmp.obtenerListaActividadesEmpleado(buscarUsuario.getCodigoUsuario());
			model.addAttribute("ActividadesEmpleado", actividadesAsignadas);
		}
		return "EmpleadoCRUD";
	}

}
