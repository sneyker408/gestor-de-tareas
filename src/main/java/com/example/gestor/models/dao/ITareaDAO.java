package com.example.gestor.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.gestor.models.entities.Tarea;

public interface ITareaDAO extends CrudRepository<Tarea,Long>{
	
	@Query("FROM Tarea c WHERE c.id=:#{#tarea.id} and c.nombre=:#{#tarea.nombre}")
	List<Tarea> findByIdNombre(Tarea tarea);
}
