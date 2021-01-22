package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.example.demo.entities.Reserva;
import com.example.demo.entities.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {

	@Override
	@Query(value = "SELECT * FROM sala WHERE status = 1", nativeQuery = true)
	public Page<Sala> findAll(Pageable page);

	public Optional<Sala> findByStatus(boolean status);

	//http://localhost:9001/api/v1/sala/getIdSala?nombre=salaA
	@Query(value = "SELECT id FROM sala WHERE nombre_de_sala = ?1 AND STATUS = TRUE", nativeQuery = true)
	int getIdSala(String data);	

	
}