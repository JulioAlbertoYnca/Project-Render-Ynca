package pe.edu.cibertec.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import pe.edu.cibertec.servicesImpl.TipoEmpleadoServices;
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
	@Autowired
	private TipoEmpleadoServices serTipo;
	
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		
		Usuario buscarUsuario = serUsu.obtenerUsuario();
		
		model.addAttribute("usuarios", serUsu.listarTodos());
		model.addAttribute("roles", serRol.listarTodos());
		model.addAttribute("tipos", serTipo.listarTodos());
		
		model.addAttribute("usuarioACtual", buscarUsuario);
		return "usuarioCRUD";
	}
	
		
	
	@RequestMapping("/registrar")
	public String grabar(
			@RequestParam("codigo") Integer cod,
			@RequestParam("userName") String user,
			@RequestParam("password") String pas,
			@RequestParam("nombre") String nom,
			@RequestParam("apellido") String ape,
			@RequestParam(value="rol", required = true) Integer rol,
			@RequestParam(value="dni", required = false) String dni,
			@RequestParam(value="telefono", required = false) String tele,
			@RequestParam(value="fechaNacimiento",required = false) String fecha,
			@RequestParam(value="tipo",required = false) Integer tip,
			RedirectAttributes redirect) {
			
			String infoPassword= pas;
			BCryptPasswordEncoder passwordEncoder= new  BCryptPasswordEncoder();
			String PasEncryp = passwordEncoder.encode(infoPassword);
		
		
		try {
			Usuario u = new Usuario();
				u.setUserName(user);
				u.setPassword(PasEncryp);
				u.setNombre(nom);
				u.setApellido(ape);
				Rol r = new Rol();
					r.setIdRol(rol);
					u.setTbRol(r);
			serUsu.registrar(u);
			Integer codResgistro = u.getCodigoUsuario();
			if (rol==2) {
				Jefe j = new Jefe();
				j.setCodigoJefe(codResgistro);
				j.setUserName(user);
				j.setPassword(PasEncryp);
				j.setNombre(nom);
				j.setApellido(ape);
				j.setDni(dni);
				j.setTelefono(tele);
				j.setFechaNacimiento(LocalDate.parse(fecha));
				serJef.registrar(j);
			} else if (rol==3) {
				Empleado ep = new Empleado();
				ep.setCodigoEmpleado(codResgistro);
				ep.setUserName(user);
				ep.setPassword(PasEncryp);
				ep.setNombre(nom);
				ep.setApellido(ape);
				ep.setDni(dni);
				ep.setTelefono(tele);
				ep.setFechaNacimiento(LocalDate.parse(fecha));
				TipoEmpleado t = new TipoEmpleado();
				t.setIdTipo(tip);
				ep.setTbTipoE(t);
				serEm.registrar(ep);
			}
			
			if (cod==0) {
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
