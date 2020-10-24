package com.tdc.chamado.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.tdc.chamado.entity.Chamado;

public interface ChamadoRepository extends Repository<Chamado, Integer>{

	Optional<Chamado> findByID(Integer id);
	
	Chamado Save(Chamado chamado);

}
