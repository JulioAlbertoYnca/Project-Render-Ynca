package pe.edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.cibertec.entity.Enlace;
import pe.edu.cibertec.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	
	// para el login
	@Query("select u from Usuario u where u.userName=?1")
	public Usuario iniciarSesion(String vUserName);
	
	// Para la ruta de enlaces
	@Query("select e from Rol_has_Enlace re join re.tbEnlaceR e where re.tbRolE.idRol=?1")
	public List<Enlace> travelEnlaceUsuario(int codRol);
	
	/*Para obtener el usuario logeado*/
	@Query("Select us from Usuario us where us.userName = ?1")
	public Usuario buscarPorUserName(String vuserName2);
}
