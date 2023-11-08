package pe.edu.cibertec.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.servicesImpl.UsuarioServices;

@Service
public class Security implements UserDetailsService{

	@Autowired
	private UsuarioServices serUsu;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails obj = null;
		Usuario bean = serUsu.loginUsuario(username);
		Set<GrantedAuthority>rol=new HashSet<GrantedAuthority>();
		rol.add(new SimpleGrantedAuthority(bean.getTbRol().getDescripcion()));
		obj=new User(username, bean.getPassword(), rol);
		return obj;
	}

}
