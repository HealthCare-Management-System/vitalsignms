package com.citius.vitalsignms.service;

import java.util.List;

import com.model.VitalSignsDto;

public interface VitalSignsService {

	public List<VitalSignsDto> listAll();

	public VitalSignsDto save(VitalSignsDto vitalSigns);

	public VitalSignsDto get(Integer id);
	
	public VitalSignsDto getByMeetingId(Integer id,String meetingid);
	
	public VitalSignsDto getByPatientId(Integer id);
	public VitalSignsDto getByPatientIdAndByMeetingId(Integer id,String meetingid);

	public void delete(Integer id);
}
