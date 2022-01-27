package com.citius.vitalsignms.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citius.vitalsignms.controller.VitalSignsController;
import com.citius.vitalsignms.entity.VitalSigns;
import com.citius.vitalsignms.exceptions.CustomException;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(VitalSignsController.class);

	@Override
	public List<VitalSignsDto> listAll() throws CustomException {
		List<VitalSignsDto> dtolist = new ArrayList<>();
		List<VitalSigns> entitylist = repo.findAll();
		LOGGER.info("list has been fetched : {}", entitylist);
		for (VitalSigns vs : entitylist) {

			dtolist.add(convertEntityToDto(vs));
		}
		return dtolist;
	}

	@Override
	public VitalSignsDto save(VitalSignsDto vitalSignsDto) throws CustomException {

		vitalSignsDto.setDateTime(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString());

		VitalSigns newobj;
		try {
			VitalSigns exist = repo.findByPatientInfoIdAndMeetingid(vitalSignsDto.getPatientInfoId().getId(),
					vitalSignsDto.getMeetingid());
			if (exist != null) {
				repo.delete(exist);
			}
			newobj = repo.save(convertDtoToEntity(vitalSignsDto));
		} catch (Exception ex) {
			throw new CustomException(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		}

		return convertEntityToDto(newobj);
	}

	@Override
	public VitalSignsDto get(Integer id) throws CustomException {

		VitalSigns exist;
		try {

			exist = repo.findById(id).get();
		} catch (Exception ex) {
			throw new CustomException(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		}

		return convertEntityToDto(exist);
	}

	@Override
	public VitalSignsDto getByMeetingId(Integer id, String meetingid) throws CustomException {
		VitalSigns exist;
		try {
			exist = repo.findByPatientInfoIdAndMeetingid(id, meetingid);
		} catch (Exception ex) {
			throw new CustomException(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		}
		return convertEntityToDto(exist);
	}

	@Override
	public VitalSignsDto getByPatientId(Integer id) throws CustomException {

		VitalSigns exist;
		try {
			exist = repo.findByPatientInfoId(id);
		} catch (Exception ex) {
			throw new CustomException(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		}

		return convertEntityToDto(exist);
	}

	@Override
	public VitalSignsDto getByPatientIdAndByMeetingId(Integer id, String meetingid) throws CustomException {
		VitalSigns exist;
		try {
			exist = repo.findByPatientInfoIdAndMeetingid(id, meetingid);
		} catch (Exception ex) {
			throw new CustomException(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		}		
		return convertEntityToDto(exist);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public UserDto getUserDtoFromUserMs(int id) throws CustomException {
		ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:8081/users/users/" + id,
				UserDto.class);
		return response.getBody();
	}

	public PatientDetailsDto getPatientInfoDtoFromPatientMs(int id) throws CustomException {
		ResponseEntity<PatientDetailsDto> response = restTemplate
				.getForEntity("http://localhost:8084/patientdetails/" + id, PatientDetailsDto.class);
		return response.getBody();
	}

	public VitalSignsDto convertEntityToDto(VitalSigns vitalSigns) throws CustomException {
		VitalSignsDto dto = new VitalSignsDto();
		dto.setId(vitalSigns.getId());
		dto.setBloodPressure(vitalSigns.getBloodPressure());
		dto.setBodyTemperature(vitalSigns.getBodyTemperature());
		dto.setDateTime(vitalSigns.getDateTime());
		dto.setHeight(vitalSigns.getHeight());
		dto.setRespirationRate(vitalSigns.getRespirationRate());
		dto.setWeight(vitalSigns.getWeight());
		dto.setMeetingid(vitalSigns.getMeetingid());
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
		vitalSigns.setMeetingid(dto.getMeetingid());
		vitalSigns.setWeight(dto.getWeight());
		vitalSigns.setPatientInfoId(dto.getPatientInfoId().getId());
		vitalSigns.setEmployeeId(dto.getEmployeeId().getId());

		return vitalSigns;
	}

}
