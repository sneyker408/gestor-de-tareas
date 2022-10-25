package com.example.gestor.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.gestor.models.entities.Proyecto;

public interface IProyectoDAO extends CrudRepository<Proyecto,Long>{
	@Query("FROM Proyecto c WHERE c.id=:#{#proyecto.id} and c.nombre=:#{#proyecto.nombre}")
	List<Proyecto> findByIdNombre(Proyecto proyecto);
}
