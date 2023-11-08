package pe.edu.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.cibertec.entity.Enlace;
import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.servicesImpl.UsuarioServices;

@SessionAttributes({"dataUsuario", "enlaces"})
@Controller
@RequestMapping("/validar")
public class PrincipalController {
	
	@Autowired
	private UsuarioServices serUsu;
	
	@RequestMapping("/usuario")
	public String index() {
		
		return "login";
	}
	
	@RequestMapping("/intranet")
	public String intranet(Authentication auth, Model model)
	{
		String nomUsuario=auth.getName();
		Usuario bean = serUsu.loginUsuario(nomUsuario);
		List<Enlace> lista=serUsu.enlacesDelUsuario(bean.getTbRol().getIdRol());
		model.addAttribute("dataUsuario", bean.getApellido()+" "+bean.getNombre());
		model.addAttribute("enlaces", lista);
		return "intranet";
	}
	
	

}
