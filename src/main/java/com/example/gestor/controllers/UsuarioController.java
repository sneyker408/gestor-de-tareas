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

import com.example.gestor.models.entities.Usuario;
import com.example.gestor.services.IUsuarioService;

@CrossOrigin(origins = "*.*")
@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> getAll(){
		return usuarioService.findAll();			
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario = usuarioService.findBy(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
		}
		if(usuario == null) {
			response.put("message", "El usuario con ID: ".concat(id.toString().concat("no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> save(@RequestBody Usuario usuario){
		Map<String, Object> response = new HashMap<>();
		try {
			if(usuarioService.isExist(usuario).size()>0) {
				response.put("message", "Ya existe un registro con este nombre en la base de datos");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CONFLICT);
			}else {
				usuarioService.save(usuario);
			}
		}catch(DataAccessException e){
			response.put("message", "Error al insertar registro en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Usuario registrado con exito...");
		response.put("usuario", usuario);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id ){
		
		Usuario usuarioActual = usuarioService.findBy(id);
		Usuario usuarioUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if(usuarioActual == null) {
			response.put("message", "Error: no se puede editar, el usuario ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setApellido(usuario.getContrasena());
			usuarioUpdated = usuarioService.save(usuarioActual);
		}catch(DataAccessException e){
			response.put("message", "Error al actualizar  el usuario");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Usuario actualizado con exito...");
		response.put("usuario", usuarioUpdated);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> delete(@RequestBody Usuario usuario, @PathVariable Long id ){
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioService.delete(id);
		}catch(DataAccessException e){
			response.put("message", "Error al eliminar el cliente de la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Usuario eliminado con exito...");
		response.put("usuario", usuario);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	} 
}

	

