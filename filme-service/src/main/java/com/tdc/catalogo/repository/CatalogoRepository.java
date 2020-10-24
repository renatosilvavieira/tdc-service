package com.tdc.catalogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tdc.catalogo.entity.Catalogo;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {

	@Query(value = "select f from Catalogo f Join fetch f.generos gf where gf.idGenero = :idGenero ")
	Optional<List<Catalogo>> findByIdGenero(Integer idGenero);
	
	@Query(value = "select f from Catalogo f where f.idCatalogo = :idCatalogo or f.nome like :nome ")
	Optional<List<Catalogo>> consultaDetalhesCatalogo(Integer idCatalogo, String nome);

}
