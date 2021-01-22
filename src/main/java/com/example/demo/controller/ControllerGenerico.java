package com.example.demo.controller;

import org.apache.logging.log4j.Logger;

import java.io.Console;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.IservicioGenerico;

import auxiliar.Error;

public class ControllerGenerico<E, S extends IservicioGenerico<E>> {
	@Autowired
	protected S service;

	private Logger logger = LogManager.getLogger(this.getClass());

	@GetMapping("/count")
	@Transactional
	public ResponseEntity<?> getCount(@RequestParam(value = "size", defaultValue = "20") int size) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body("{\"pages\": " + service.countPages(size) + "}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e, 500));
		}
	}

	@GetMapping("")
	@Transactional
	public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "20") int size) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll(page, size));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e, 500));
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<?> getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e, 404));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e, 500));
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity<?> post(@RequestBody E salaForm) {
		try {
			System.out.println("ESTOY USANDO POST EN BACKEND TRY");
			return ResponseEntity.status(HttpStatus.OK).body(service.save(salaForm));
		} catch (Exception e) {
			System.out.println("ESTOY USANDO POST EN BACKEND CATCH");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e, 500));
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> put(@PathVariable int id, @RequestBody E salaForm) {
		try {
			System.out.println("ESTOY USANDO PUT EN BACKEND TRY");
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, salaForm));
		} catch (NoSuchElementException e) {
			System.out.println("ESTOY USANDO PUT EN BACKEND CATCH 1");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e, 404));
		} catch (Exception e) {
			System.out.println("ESTOY USANDO PUT EN BACKEND CATCH 2");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e, 500));
		}

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable int id) {
		try {
			System.out.println("ESTOY USANDO DELETE EN BACKEND TRY");
			return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
		} catch (NoSuchElementException e) {
			System.out.println("ESTOY USANDO DELETE EN BACKEND CATCH 1");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e, 404));
		} catch (Exception e) {
			System.out.println("ESTOY USANDO DELETE EN BACKEND CATCH 1");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e, 500));
		}
	}

}