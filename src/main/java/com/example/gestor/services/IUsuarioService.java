package com.example.gestor.services;

import java.util.List;

import com.example.gestor.models.entities.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findBy(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
	
	public List<Usuario> isExist(Usuario usuario);
}
