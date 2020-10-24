package com.tdc.catalogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tdc.catalogo.entity.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {

	@Query(value = "select f from Filme f Join fetch f.generos gf where gf.idGenero = :idGenero ")
	Optional<List<Filme>> findByIdGenero(Integer idGenero);

	Optional<List<Filme>> findByidCatalogoOrNomeLike(Integer idCatalogo, String nome);

}
