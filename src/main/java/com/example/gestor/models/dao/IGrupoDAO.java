package com.example.gestor.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.gestor.models.entities.Grupo;

public interface IGrupoDAO extends CrudRepository<Grupo,Long>{
	
	@Query("FROM Grupo c WHERE c.id=:#{#grupo.id} and c.nombre=:#{#grupo.nombre}")
	List<Grupo> findByIdNombre(Grupo grupo);
}
