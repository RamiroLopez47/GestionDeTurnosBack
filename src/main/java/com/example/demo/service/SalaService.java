package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Reserva;
import com.example.demo.entities.Sala;
import com.example.demo.repository.SalaRepository;

@Service
public class SalaService extends ServicioGenerico<Sala, SalaRepository> {
	
	public int getIdSala(String nombre) {		
		int salas = repository.getIdSala(nombre);
			return salas;		
	}
	
	
	
}
