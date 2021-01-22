package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Sala;

import com.example.demo.service.SalaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })

@RequestMapping(path = "api/v1/sala")
public class SalaController extends ControllerGenerico<Sala, SalaService> {

	
	@GetMapping("/getIdSala")	
	public ResponseEntity getIdSala(@RequestParam(value = "") String nombre) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getIdSala(nombre));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"\"Error en buscar estado: \"" + e.getMessage() + "\"}");
		}
	}
	
	
	
}
