package com.example.gestor.services;

import java.util.List;

import com.example.gestor.models.entities.Proyecto;

public interface IProyectoService {
	
	public List<Proyecto> findAll();
	
	public Proyecto findBy(Long id);
	
	public Proyecto save(Proyecto proyecto);
	
	public void delete(Long id);
	
	public List<Proyecto> isExist(Proyecto proyecto);
}
