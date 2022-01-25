package com.citius.vitalsignms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citius.vitalsignms.entity.VitalSigns;

public interface VitalSignsRepo extends JpaRepository<VitalSigns, Integer> {

	VitalSigns findByPatientInfoId(int id);
	
	
	VitalSigns findByPatientInfoIdAndMeetingid(int id,String meetingid);
}
