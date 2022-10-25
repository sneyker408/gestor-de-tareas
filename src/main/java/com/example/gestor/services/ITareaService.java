package com.example.gestor.services;

import java.util.List;

import com.example.gestor.models.entities.Tarea;

public interface ITareaService {
	
	public List<Tarea> findAll();
	
	public Tarea findBy(Long id);
	
	public Tarea save(Tarea tarea);
	
	public void delete(Long id);
	
	public List<Tarea> isExist(Tarea tarea);
}

