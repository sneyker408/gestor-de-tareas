package com.example.gestor.services;

import java.util.List;

import com.example.gestor.models.entities.Grupo;

public interface IGrupoService {
	
	public List<Grupo> findAll();
	
	public Grupo findBy(Long id);
	
	public Grupo save(Grupo grupo);
	
	public void delete(Long id);
	
	public List<Grupo> isExist(Grupo grupo);
}
