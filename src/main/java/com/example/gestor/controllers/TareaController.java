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

import com.example.gestor.models.entities.Tarea;
import com.example.gestor.services.ITareaService;

@CrossOrigin(origins = "*.*")
@RestController
@RequestMapping("/api")
public class TareaController {
	
	@Autowired
	private ITareaService tareaService;
	
	@GetMapping("/tareas")
	public List<Tarea> getAll(){
		return tareaService.findAll();			
	}
	
	@GetMapping("/tareas/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Tarea tarea = null;
		Map<String, Object> response = new HashMap<>();
		try {
			tarea = tareaService.findBy(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
		}
		if(tarea == null) {
			response.put("message", "LA tarea con ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Tarea>(tarea,HttpStatus.OK);
	}
	
	@PostMapping("/tareas")
	public ResponseEntity<?> save(@RequestBody Tarea tarea){
		Map<String, Object> response = new HashMap<>();
		try {
			if(tareaService.isExist(tarea).size()>0) {
				response.put("message", "Ya existe un registro con este nombre en la base de datos");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CONFLICT);
			}else {
				tareaService.save(tarea);
			}
		}catch(DataAccessException e){
			response.put("message", "Error al insertar registro en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Grupo registrado con exito...");
		response.put("tarea", tarea);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tareas/{id}")
	public ResponseEntity<?> update(@RequestBody Tarea tarea, @PathVariable Long id ){
		
		Tarea tareaActual = tareaService.findBy(id);
		Tarea tareaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if(tareaActual == null) {
			response.put("message", "Error: no se puede editar, la tarea ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			tareaActual.setNombre(tarea.getNombre());
			tareaActual.setEstado(tarea.getEstado());
			tareaUpdated = tareaService.save(tareaActual);
		}catch(DataAccessException e){
			response.put("message", "Error al actualizar  la tarea");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Usuario actualizado con exito...");
		response.put("tarea", tareaUpdated);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/tareas/{id}")
	public ResponseEntity<?> delete(@RequestBody Tarea tarea, @PathVariable Long id ){
		Map<String, Object> response = new HashMap<>();
		try {
			tareaService.delete(id);
		}catch(DataAccessException e){
			response.put("message", "Error al eliminar la tarea de la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Tarea eliminado con exito...");
		response.put("tarea", tarea);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	} 

}
