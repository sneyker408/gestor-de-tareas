package com.example.gestor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gestor.models.entities.Detalle_tarea;
import com.example.gestor.models.dao.IDetalle_tareaDAO;
@Service
public class Detalle_tareaImpl implements IDetalle_tareaService{

	@Autowired
	private IDetalle_tareaDAO detalle_tareaDAO;
	
	@Override
	public List<Detalle_tarea> findAll() {
		return (List<Detalle_tarea>)detalle_tareaDAO.findAll();
	}

	@Override
	public Detalle_tarea findBy(Long id) {
		return detalle_tareaDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Detalle_tarea save(Detalle_tarea detalle_tarea) {
		return  detalle_tareaDAO.save(detalle_tarea);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		detalle_tareaDAO.deleteById(id);
		
	}

	@Override
	public List<Detalle_tarea> isExist(Detalle_tarea detalle_tarea) {
		return detalle_tareaDAO.findById(detalle_tarea);
	}
	
}
