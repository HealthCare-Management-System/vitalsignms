package com.citius.vitalsignms.service;

import java.util.List;

import com.model.VitalSignsDto;

public interface VitalSignsService {

	public List<VitalSignsDto> listAll();

	public VitalSignsDto save(VitalSignsDto vitalSigns);

	public VitalSignsDto get(Integer id);

	public void delete(Integer id);
}
