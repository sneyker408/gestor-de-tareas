 package com.example.gestor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestor.models.dao.IProyectoDAO;
import com.example.gestor.models.entities.Proyecto;

@Service
public class ProyectoServiceImpl implements IProyectoService{

	@Autowired
	private IProyectoDAO proyectoDAO;
	
	@Override
	public List<Proyecto> findAll() {
		return (List<Proyecto>)proyectoDAO.findAll();
	}

	@Override
	public Proyecto findBy(Long id) {
		return proyectoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Proyecto save(Proyecto proyecto) {
		return  proyectoDAO.save(proyecto);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		proyectoDAO.deleteById(id);
	}

	@Override
	public List<Proyecto> isExist(Proyecto proyecto) {
		return proyectoDAO.findByIdNombre(proyecto);
	}
	
}
