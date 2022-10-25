package com.example.gestor.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.gestor.models.entities.Usuario;


public interface IUsuarioDAO extends CrudRepository<Usuario,Long>{
	
	@Query("FROM Usuario c WHERE c.id=:#{#usuario.id} and c.contrasena=:#{#usuario.contrasena}")
	List<Usuario> findByIdContraseña(Usuario usuario);
}
