package com.tdc.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tdc.usuario.entity.HistoricoCatalogo;
import com.tdc.usuario.entity.Usuario;

@Repository
public interface HistoricoCatalogoRepository extends JpaRepository<HistoricoCatalogo, Integer> {

	@Query(value = "select hist from HistoricoCatalogo hist where hist.assistido = :assistido and hist.usuario.idUsuario = :idUsuario")
	Optional<List<HistoricoCatalogo>> findByAssistidoAndUsuario(Boolean assistido, Integer idUsuario);

	Optional<HistoricoCatalogo> findByIdCatalogoAndUsuario(Integer idCatalogo, Usuario usuario);

}
