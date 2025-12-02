package br.com.ifba.plantas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifba.plantas.entity.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
