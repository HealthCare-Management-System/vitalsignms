package com.citius.vitalsignms.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citius.vitalsignms.service.VitalSignsService;
import com.model.VitalSignsDto;



@RestController
@RequestMapping("/vitalsigns")
public class VitalSignsController {

	@Autowired
    private VitalSignsService service;
	
	@GetMapping
	public List<VitalSignsDto> list() {
	    return service.listAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<VitalSignsDto> get(@PathVariable Integer id) {
	    try {
	    	VitalSignsDto vitalSigns = service.get(id);
	        return new ResponseEntity<VitalSignsDto>(vitalSigns, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<VitalSignsDto>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@PostMapping
	public ResponseEntity<VitalSignsDto> add(@RequestBody VitalSignsDto vitalSigns) {
	    return new ResponseEntity<VitalSignsDto>(service.save(vitalSigns), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody VitalSignsDto vitalSigns, @PathVariable Integer id) {
	    try {
	    	VitalSignsDto exist = service.get(id);
	        service.save(vitalSigns);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
	    service.delete(id);
	}
	
	@GetMapping("/ping")
	public String greetings() {
		return "Working";
	}

}
