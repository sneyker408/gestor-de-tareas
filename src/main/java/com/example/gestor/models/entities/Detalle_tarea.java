package com.example.gestor.models.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="detalle_tareas", schema="public", catalog="gestor_db")
public class Detalle_tarea implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name = "tarea_id", referencedColumnName = "id", nullable = false)
	private Tarea tarea;
	

	public void setId(Long id) {
		this.id = id;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
}
