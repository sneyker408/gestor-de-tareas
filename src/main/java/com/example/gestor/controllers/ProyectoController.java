package com.example.gestor.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestor.models.entities.Proyecto;
import com.example.gestor.services.IProyectoService;

@CrossOrigin(origins = "*.*")
@RestController
@RequestMapping("/api")
public class ProyectoController {
	
	@Autowired
	private IProyectoService proyectoService;
	
	@GetMapping("/proyectos")
	public List<Proyecto> getAll(){
		return proyectoService.findAll();			
	}
	
	@GetMapping("/proyectos/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Proyecto proyecto = null;
		Map<String, Object> response = new HashMap<>();
		try {
			proyecto = proyectoService.findBy(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
		}
		if(proyecto == null) {
			response.put("message", "El proyecto con ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Proyecto>(proyecto,HttpStatus.OK);
	} 
	
	@PostMapping("/proyectos")
	public ResponseEntity<?> save(@RequestBody Proyecto proyecto){
		Map<String, Object> response = new HashMap<>();
		try {
			if(proyectoService.isExist(proyecto).size()>0) {
				response.put("message", "Ya existe un registro con este nombre en la base de datos");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CONFLICT);
			}else {
				proyectoService.save(proyecto);
			}
		}catch(DataAccessException e){
			response.put("message", "Error al insertar registro en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Proyecto registrado con exito...");
		response.put("proyecto", proyecto);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	

	@PutMapping("/proyectos/{id}")
	public ResponseEntity<?> update(@RequestBody Proyecto proyecto, @PathVariable Long id ){
		
		Proyecto proyectoActual = proyectoService.findBy(id);
		Proyecto proyectoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if(proyectoActual == null) {
			response.put("message", "Error: no se puede editar, el usuario ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			proyectoActual.setNombre(proyecto.getNombre());
			proyectoUpdated = proyectoService.save(proyectoActual);
		}catch(DataAccessException e){
			response.put("message", "Error al actualizar  el usuario");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Proyecto actualizado con exito...");
		response.put("proyecto", proyectoUpdated);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/proyectos/{id}")
	public ResponseEntity<?> delete(@RequestBody Proyecto proyecto, @PathVariable Long id ){
		Map<String, Object> response = new HashMap<>();
		try {
			proyectoService.delete(id);
		}catch(DataAccessException e){
			response.put("message", "Error al eliminar el proyecto de la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Proyecto eliminado con exito...");
		response.put("proyecto", proyecto);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	} 
}

