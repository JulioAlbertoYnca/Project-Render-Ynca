package pe.edu.cibertec.controller;

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
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.entity.Actividad;
import pe.edu.cibertec.servicesImpl.ActividadServices;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/actividad")
public class ActividadController {
	
	@Autowired
	private ActividadServices serAct;
	
	@GetMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("actividades", serAct.listarTodos());
		return "ActividadCRUD";
	}
	
	
	@PostMapping("/registrar")
	public ResponseEntity<Actividad> registrar(@RequestBody Actividad bean) {
		Actividad obj = serAct.registrar(bean);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}

}
