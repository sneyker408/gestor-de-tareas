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

import com.example.gestor.models.entities.Grupo;
import com.example.gestor.services.IGrupoService;

@CrossOrigin(origins = "*.*")
@RestController
@RequestMapping("/api")
public class GrupoController {
	
	@Autowired
	private IGrupoService grupoService;
	
	@GetMapping("/grupos")
	public List<Grupo> getAll(){
		return grupoService.findAll();			
	}
	
	@GetMapping("/grupo/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Grupo grupo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			grupo = grupoService.findBy(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
		}
		if(grupo == null) {
			response.put("message", "El grupo con ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Grupo>(grupo,HttpStatus.OK);
	}
	
	@PostMapping("/grupos")
	public ResponseEntity<?> save(@RequestBody Grupo grupo){
		Map<String, Object> response = new HashMap<>();
		try {
			if(grupoService.isExist(grupo).size()>0) {
				response.put("message", "Ya existe un registro con este nombre en la base de datos");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CONFLICT);
			}else {
				grupoService.save(grupo);
			}
		}catch(DataAccessException e){
			response.put("message", "Error al insertar registro en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Grupo registrado con exito...");
		response.put("grupo", grupo);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/grupos/{id}")
	public ResponseEntity<?> update(@RequestBody Grupo grupo, @PathVariable Long id ){
		
		Grupo grupoActual = grupoService.findBy(id);
		Grupo grupoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if(grupoActual == null) {
			response.put("message", "Error: no se puede editar, el grupo ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			grupoActual.setNombre(grupo.getNombre());
			grupoUpdated = grupoService.save(grupoActual);
		}catch(DataAccessException e){
			response.put("message", "Error al actualizar  el grupo");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Grupo actualizado con exito...");
		response.put("grupo", grupoUpdated);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/grupos/{id}")
	public ResponseEntity<?> delete(@RequestBody Grupo grupo, @PathVariable Long id ){
		Map<String, Object> response = new HashMap<>();
		try {
			grupoService.delete(id);
		}catch(DataAccessException e){
			response.put("message", "Error al eliminar el grupo de la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Grupo eliminado con exito...");
		response.put("grupo", grupo);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	} 
	
}
