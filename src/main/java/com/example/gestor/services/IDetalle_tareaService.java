package com.example.gestor.services;

import java.util.List;

import com.example.gestor.models.entities.Detalle_tarea;

public interface IDetalle_tareaService {
	
public List<Detalle_tarea> findAll();
	
	public Detalle_tarea findBy(Long id);
	
	public Detalle_tarea save(Detalle_tarea detalle_tarea);
	
	public void delete(Long id);
	
	public List<Detalle_tarea> isExist(Detalle_tarea detalle_tarea);
}
