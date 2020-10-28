package com.tdc.chamado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdc.chamado.entity.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
