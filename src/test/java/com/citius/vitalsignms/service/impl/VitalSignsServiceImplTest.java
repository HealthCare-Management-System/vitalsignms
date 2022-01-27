package com.citius.vitalsignms.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.citius.vitalsignms.entity.VitalSigns;
import com.citius.vitalsignms.exceptions.CustomException;
import com.citius.vitalsignms.repo.VitalSignsRepo;
import com.model.PatientDetailsDto;
import com.model.UserDto;
import com.model.VitalSignsDto;     

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VitalSignsServiceImplTest {

	@Mock
	VitalSignsRepo vitalSignRepository;

	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	VitalSignsServiceImpl vitalService;

	@Test
	public void vital_gelAll_success() throws CustomException {
		List<VitalSigns> signs = Arrays.asList(new VitalSigns(1, "h1", "w1", "bp1", "bt1", "rr1", "dt1", "meet1", 4, 4),
				new VitalSigns(2, "h2", "w2", "bp2", "bt2", "rr2", "dt2", "meet2", 4, 4));

		PatientDetailsDto patdto = new PatientDetailsDto();
		UserDto userdto=new UserDto();
		
		List<VitalSignsDto> signsdto = Arrays.asList(new VitalSignsDto(1, "h1", "w1", "bp1", "bt1", "rr1", "dt1",patdto , userdto, "meet1"),
				new VitalSignsDto(2, "h2", "w2", "bp2", "bt2", "rr2", "dt2",patdto , userdto, "meet2"));
		
		when(vitalSignRepository.findAll()).thenReturn(signs);

		when(restTemplate.getForEntity("http://localhost:8084/patientdetails/4", PatientDetailsDto.class))
				.thenReturn(new ResponseEntity<PatientDetailsDto>(patdto, HttpStatus.OK));

		when(restTemplate.getForEntity("http://localhost:8081/users/users/4", UserDto.class))
		.thenReturn(new ResponseEntity<UserDto>(userdto, HttpStatus.OK));
		
		
		List<VitalSignsDto> actualList = vitalService.listAll();

		assertThat(signsdto, equalTo(actualList));
	}

}
