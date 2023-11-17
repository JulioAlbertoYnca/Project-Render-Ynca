package pe.edu.cibertec.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import pe.edu.cibertec.entity.Actividad;
import pe.edu.cibertec.entity.Actividad_Has_Empleado;
import pe.edu.cibertec.entity.DetalleEmpleado;
import pe.edu.cibertec.entity.Empleado;
import pe.edu.cibertec.entity.Jefe;
import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.servicesImpl.ActividadServices;
import pe.edu.cibertec.servicesImpl.EmpleadoServices;
import pe.edu.cibertec.servicesImpl.UsuarioServices;

@Controller
@RequestMapping("/actividad")
public class ActividadController {
	
	@Autowired
	private ActividadServices serAct;
	
	@Autowired
	private EmpleadoServices serEmp;
	
	@Autowired
	private UsuarioServices serUsu;
	
	@GetMapping("/lista")
	public String lista(Model model) {
		
		Usuario buscarUsuario = serUsu.obtenerUsuario();
		if (buscarUsuario != null) {
			model.addAttribute("usuarioActual", buscarUsuario);
		}
		
		model.addAttribute("actividades", serAct.listarTodos());
		return "ActividadCRUD";
	}
	
	
	@RequestMapping("/listaEmpleadoJSON")
	@ResponseBody
	public List<Empleado> listaEmpleadosJSON(@RequestParam("userName") String usName){
		return serEmp.listaPorUserName(usName);
	}
	
	/*Para adicionar a un lista xd*/
	@RequestMapping("/adicionar")
	@ResponseBody
	public List<DetalleEmpleado> adicionar(	@RequestParam("codigo") int cod,
											@RequestParam("userName") String user,
											@RequestParam("nombre") String nom,
											@RequestParam("apellido") String ape,
											HttpSession sesion)
	{
		List<DetalleEmpleado> listaE = null;
		
		try {
			if(sesion.getAttribute("data")==null)
				listaE=new ArrayList<DetalleEmpleado>();
			else
				listaE=(List<DetalleEmpleado>) sesion.getAttribute("data");
				DetalleEmpleado det = new DetalleEmpleado();
					det.setCodigo(cod);
					det.setUserName(user);
					det.setNombre(nom);
					det.setApellido(ape);
				listaE.add(det);
				
				sesion.setAttribute("data", listaE);
			
			//System.out.println(listaE);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return listaE;
	}

	@RequestMapping("/grabar")
	public String grabar(	@RequestParam("codigo") int cod,
							@RequestParam("jefe") String use,
							@RequestParam("fechaInicio") String fecIn,
							@RequestParam("fechaFin") String fecFi,
							//@SessionAttribute("CODIGO_USUARIO") int codUse,
							HttpSession session) {
		try {
			
			Actividad ac = new Actividad();
			ac.setNombreActividad("Limpieza");
			ac.setDescripcion("Datos adicionales");
			ac.setFechaInicio(LocalDate.parse(fecIn));
			ac.setFechaFin(LocalDate.parse(fecFi));
			ac.setEstado(1);
			Jefe j = new Jefe();
			j.setCodigoJefe(cod);
			ac.setTbJefeA(j);
			
			// Detalle
			List<Actividad_Has_Empleado> lista = new ArrayList<Actividad_Has_Empleado>();
			List<DetalleEmpleado> datos = (List<DetalleEmpleado>) session.getAttribute("data");
				for(DetalleEmpleado det:datos) {
					
					Actividad_Has_Empleado ahe = new Actividad_Has_Empleado();
						
						Empleado emp = new Empleado();
						emp.setCodigoEmpleado(det.getCodigo());	
						ahe.setTbEmpleadoA(emp);
						lista.add(ahe);
				}
				ac.setTbActividadE(lista);
				
				serAct.registrarActividad(ac);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/actividad/lista";
	}
	
}
