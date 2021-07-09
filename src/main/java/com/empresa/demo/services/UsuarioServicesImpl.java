package com.empresa.demo.services;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.empresa.demo.dao.IUsuario;
import com.empresa.demo.model.Rol;
import com.empresa.demo.model.Usuario;
 

@Service("userService")
public class UsuarioServicesImpl implements UserDetailsService,UsuarioService {
	
	@Autowired
	@Qualifier("userRepository")
	private IUsuario userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.empresa.demo.model.Usuario user=userRepository.findByUsername(username);
		List<GrantedAuthority> authorities=buildAuthorities(user.getUserRole());
		
		return buildUser(user, authorities);
	}
	
	private User buildUser(com.empresa.demo.model.Usuario user, List<GrantedAuthority> authorities) {
		
		return new User(user.getUsername(),user.getPassword(), user.isEnabled(), true, true, true, authorities);
		
	}
	
	private List<GrantedAuthority> buildAuthorities(Set<Rol> userRole){
		
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for(Rol urol : userRole) {
			auths.add(new SimpleGrantedAuthority(urol.getNombreRol()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

	@Override
	public void eliminar(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public int guardar(Usuario us) {
		int res=0;
		String password =us.getPassword();
		us.setPassword(passEncoder.encode(password));
		Usuario login=userRepository.save(us);
		if(!login.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	



}
