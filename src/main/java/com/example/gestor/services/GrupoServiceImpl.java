package com.example.gestor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestor.models.dao.IGrupoDAO;
import com.example.gestor.models.entities.Grupo;
@Service
public  class GrupoServiceImpl implements IGrupoService{
	
	@Autowired
	private IGrupoDAO grupoDAO;

	@Override
	public List<Grupo> findAll() {
		return (List<Grupo>)grupoDAO.findAll();
	}

	@Override
	public Grupo findBy(Long id) {
		return grupoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Grupo save(Grupo grupo) {
		return  grupoDAO.save(grupo);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		grupoDAO.deleteById(id);
	}

	@Override
	public List<Grupo> isExist(Grupo grupo) {
		return grupoDAO.findByIdNombre(grupo);
	}
	
	
}
