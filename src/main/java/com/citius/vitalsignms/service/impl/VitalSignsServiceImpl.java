package com.citius.vitalsignms.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citius.vitalsignms.entity.VitalSigns;
import com.citius.vitalsignms.repo.VitalSignsRepo;
import com.citius.vitalsignms.service.VitalSignsService;
import com.model.PatientDetailsDto;
import com.model.UserDto;
import com.model.VitalSignsDto;

@Service
public class VitalSignsServiceImpl implements VitalSignsService {

	@Autowired
	private VitalSignsRepo repo;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<VitalSignsDto> listAll() {
		List<VitalSignsDto> dtolist = new ArrayList<>();
		List<VitalSigns> entitylist = repo.findAll();
		for (VitalSigns vs : entitylist) {

			dtolist.add(convertEntityToDto(vs));
		}
		return dtolist;
	}

	@Override
	public VitalSignsDto save(VitalSignsDto vitalSignsDto) {

		vitalSignsDto.setDateTime(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString());

		VitalSigns exist = repo.findByPatientInfoId(vitalSignsDto.getPatientInfoId().getId());
		if (exist != null) {
			repo.delete(exist);
		}
		return convertEntityToDto(repo.save(convertDtoToEntity(vitalSignsDto)));
	}

	@Override
	public VitalSignsDto get(Integer id) {

		return convertEntityToDto(repo.findById(id).get());
	}

	@Override
	public VitalSignsDto getByPatientId(Integer id) {

		return convertEntityToDto(repo.findByPatientInfoId(id));
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public UserDto getUserDtoFromUserMs(int id) {
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8081/users/users/" + id,
				UserDto.class);
		return response.getBody();
	}

	public PatientDetailsDto getPatientInfoDtoFromPatientMs(int id) {
		ResponseEntity<PatientDetailsDto> response = restTemplate
				.getForEntity("http://localhost:8084/patientdetails/" + id, PatientDetailsDto.class);
		return response.getBody();
	}

	public VitalSignsDto convertEntityToDto(VitalSigns vitalSigns) {
		VitalSignsDto dto = new VitalSignsDto();
		dto.setId(vitalSigns.getId());
		dto.setBloodPressure(vitalSigns.getBloodPressure());
		dto.setBodyTemperature(vitalSigns.getBodyTemperature());
		dto.setDateTime(vitalSigns.getDateTime());
		dto.setHeight(vitalSigns.getHeight());
		dto.setRespirationRate(vitalSigns.getRespirationRate());
		dto.setWeight(vitalSigns.getWeight());

		dto.setEmployeeId(getUserDtoFromUserMs(vitalSigns.getEmployeeId()));
		dto.setPatientInfoId(getPatientInfoDtoFromPatientMs(vitalSigns.getPatientInfoId()));

		return dto;

	}

	public VitalSigns convertDtoToEntity(VitalSignsDto dto) {
		VitalSigns vitalSigns = new VitalSigns();
		vitalSigns.setId(dto.getId());
		vitalSigns.setBloodPressure(dto.getBloodPressure());
		vitalSigns.setBodyTemperature(dto.getBodyTemperature());
		vitalSigns.setDateTime(dto.getDateTime());
		vitalSigns.setHeight(dto.getHeight());
		vitalSigns.setRespirationRate(dto.getRespirationRate());
		vitalSigns.setWeight(dto.getWeight());
		vitalSigns.setPatientInfoId(dto.getPatientInfoId().getId());
		vitalSigns.setEmployeeId(dto.getEmployeeId().getId());

		return vitalSigns;
	}

}
