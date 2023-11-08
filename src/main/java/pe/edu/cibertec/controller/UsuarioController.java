package pe.edu.cibertec.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.cibertec.entity.Actividad;
import pe.edu.cibertec.entity.Empleado;
import pe.edu.cibertec.entity.Jefe;
import pe.edu.cibertec.entity.Rol;
import pe.edu.cibertec.entity.TipoEmpleado;
import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.servicesImpl.EmpleadoServices;
import pe.edu.cibertec.servicesImpl.JefeServices;
import pe.edu.cibertec.servicesImpl.RolServices;
import pe.edu.cibertec.servicesImpl.UsuarioServices;

//@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServices serUsu;
	@Autowired
	private RolServices serRol;
	@Autowired
	private JefeServices serJef;
	@Autowired 
	private EmpleadoServices serEm;
	
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("usuarios", serUsu.listarTodos());
		model.addAttribute("roles", serRol.listarTodos());
		return "usuarioCRUD";
	}
	
		
	
	@RequestMapping("/registrar")
	public String grabar(
			@RequestParam("codigo") Integer cod,
			@RequestParam("userName") String user,
			@RequestParam("password") String pas,
			@RequestParam("nombre") String nom,
			@RequestParam("apellido") String ape,
			@RequestParam("rol") Integer rol,
			RedirectAttributes redirect) {
		try {
			Usuario u = new Usuario();
			u.setUserName(user);
			u.setPassword(pas);
			u.setNombre(nom);
			u.setApellido(ape);
			Rol r = new Rol();
			if (rol==2) {
				Jefe j = new Jefe();
				j.setUserName(user);
				j.setPassword(pas);
				j.setNombre(nom);
				j.setApellido(ape);
				j.setDni("72280526");
				j.setTelefono("965247510");
				j.setFechaNacimiento(LocalDate.parse("2002-02-04"));
				serJef.registrar(j);
			} else if (rol==3) {
				Empleado ep = new Empleado();
				ep.setUserName(user);
				ep.setPassword(pas);
				ep.setNombre(nom);
				ep.setApellido(ape);
				ep.setDni("72280526");
				ep.setTelefono("965247510");
				ep.setFechaNacimiento(LocalDate.parse("2002-02-04"));
				TipoEmpleado t = new TipoEmpleado();
				t.setIdTipo(1);
				ep.setTbTipoE(t);
				serEm.registrar(ep);
			}
			r.setIdRol(rol);
			u.setTbRol(r);
			if (cod==0) {
				serUsu.registrar(u);
				redirect.addFlashAttribute("Mensaje", "Nuevo Usuario registrado");
			}else {
				u.setCodigoUsuario(cod);
				serUsu.actualizar(u);
				redirect.addFlashAttribute("Mensaje", "Usuario Actualizado");
			}
					
		} catch (Exception e) {
			redirect.addFlashAttribute("Mensaje", "Problemas en el registro de un nuevo usuario");
			e.printStackTrace();
		}
		
		return "redirect:/usuario/lista";
	}
	

}
