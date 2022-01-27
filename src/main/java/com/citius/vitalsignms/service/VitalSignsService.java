package com.citius.vitalsignms.service;

import java.util.List;

import com.citius.vitalsignms.exceptions.CustomException;
import com.model.VitalSignsDto;

public interface VitalSignsService {

	public List<VitalSignsDto> listAll() throws CustomException;

	public VitalSignsDto save(VitalSignsDto vitalSigns) throws CustomException;

	public VitalSignsDto get(Integer id) throws CustomException;
	
	public VitalSignsDto getByMeetingId(Integer id,String meetingid) throws CustomException;
	
	public VitalSignsDto getByPatientId(Integer id) throws CustomException;
	public VitalSignsDto getByPatientIdAndByMeetingId(Integer id,String meetingid) throws CustomException;

	public void delete(Integer id);
}
