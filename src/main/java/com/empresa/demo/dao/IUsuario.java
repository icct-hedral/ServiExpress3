package com.empresa.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.demo.model.Usuario;

@Repository("userRepository")
public interface IUsuario extends JpaRepository<Usuario, String> {


	public abstract Usuario findByUsername(String username);

}
