package com.example.gestor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestor.models.dao.ITareaDAO;
import com.example.gestor.models.entities.Tarea;
@Service
public class TareaServiceImpl implements ITareaService{
	
	@Autowired
	private ITareaDAO tareaDAO;

	@Override
	public List<Tarea> findAll() {
		return (List<Tarea>)tareaDAO.findAll();
	}

	@Override
	public Tarea findBy(Long id) {
		return tareaDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Tarea save(Tarea tarea) {
		return  tareaDAO.save(tarea);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		tareaDAO.deleteById(id);
		
	}

	@Override
	public List<Tarea> isExist(Tarea tarea) {
		return tareaDAO.findByIdNombre(tarea);
	}

}
