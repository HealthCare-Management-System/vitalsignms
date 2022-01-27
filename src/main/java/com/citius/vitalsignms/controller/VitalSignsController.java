package com.citius.vitalsignms.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.citius.vitalsignms.exceptions.CustomException;
import com.citius.vitalsignms.service.VitalSignsService;
import com.model.VitalSignsDto;

@RestController
@RequestMapping("/vitalsigns")
public class VitalSignsController {

	@Autowired
	private VitalSignsService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(VitalSignsController.class);

	@GetMapping("/ping")
	public String greetings() {
		return "Working";
	}

	@GetMapping
	public ResponseEntity<List<VitalSignsDto>> list() throws CustomException {
		LOGGER.info("fetching list of Vital Signs");
		return new ResponseEntity<List<VitalSignsDto>>(service.listAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VitalSignsDto> get(@PathVariable Integer id) throws CustomException {
		try {
			VitalSignsDto vitalSigns = service.get(id);
			return new ResponseEntity<VitalSignsDto>(vitalSigns, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<VitalSignsDto>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/{meetingid}")
	public ResponseEntity<VitalSignsDto> getbyMeetingId(@PathVariable Integer id, @PathVariable String meetingid)
			throws CustomException {
		try {
			VitalSignsDto vitalSigns = service.getByMeetingId(id, meetingid);
			return new ResponseEntity<VitalSignsDto>(vitalSigns, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<VitalSignsDto>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/patient/{id}")
	public ResponseEntity<VitalSignsDto> getByPatientId(@PathVariable Integer id) throws CustomException {
		try {
			VitalSignsDto vitalSigns = service.getByPatientId(id);
			return new ResponseEntity<VitalSignsDto>(vitalSigns, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<VitalSignsDto>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/patient/{id}/{meetingid}")
	public ResponseEntity<VitalSignsDto> getByPatientIdAndMeetingId(@PathVariable Integer id,
			@PathVariable String meetingid) throws CustomException {
		try {
			VitalSignsDto vitalSigns = service.getByPatientIdAndByMeetingId(id, meetingid);
			return new ResponseEntity<VitalSignsDto>(vitalSigns, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<VitalSignsDto>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<VitalSignsDto> add(@RequestBody VitalSignsDto vitalSigns) throws CustomException {
		System.out.println("inside Vital controller");
		return new ResponseEntity<VitalSignsDto>(service.save(vitalSigns), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody VitalSignsDto vitalSigns, @PathVariable Integer id)
			throws CustomException {
		try {
			VitalSignsDto exist = service.get(id);
			if (null == exist) {
				throw new CustomException(HttpStatus.NOT_FOUND.value(), "VitalSign with this Id Not found");
			}
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

}
