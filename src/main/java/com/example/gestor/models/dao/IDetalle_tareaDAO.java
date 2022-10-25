package com.example.gestor.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.gestor.models.entities.Detalle_tarea;


public interface IDetalle_tareaDAO extends CrudRepository<Detalle_tarea,Long>{
	@Query("FROM Detalle_tarea c WHERE c.id=:#{#detalle_tarea.id}")
	List<Detalle_tarea> findById(Detalle_tarea detalle_tarea);
}
