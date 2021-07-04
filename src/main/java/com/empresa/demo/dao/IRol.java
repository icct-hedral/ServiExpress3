package com.empresa.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.demo.model.Rol;

@Repository("rolDao")
public interface IRol extends JpaRepository<Rol, Integer>{

}
