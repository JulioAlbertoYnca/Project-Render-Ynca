package pe.edu.cibertec.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.entity.Enlace;
import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.repository.UsuarioRepository;

@Service
public class UsuarioServices extends ICRUDImpl<Usuario, Integer>{

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public JpaRepository<Usuario, Integer> getRepository() {
		return repo;
	}
	
	public Usuario loginUsuario(String vUserName) {
		return repo.iniciarSesion(vUserName);
	}

	public List<Enlace> enlacesDelUsuario(int rol){
		return repo.travelEnlaceUsuario(rol);
	}
	
	public Usuario obtenerUsuario() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return repo.buscarPorUserName(username);
	}
	
	
}
