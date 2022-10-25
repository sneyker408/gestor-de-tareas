package com.example.gestor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestor.models.dao.IUsuarioDAO;
import com.example.gestor.models.entities.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDAO.findAll();
	}

	@Override
	public Usuario findBy(Long id) {
		return usuarioDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		return  usuarioDAO.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDAO.deleteById(id);
	}

	@Override
	public List<Usuario> isExist(Usuario usuario) {
		return usuarioDAO.findByIdContraseña(usuario);
	}

	
}
