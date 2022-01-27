package com.citius.vitalsignms.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.ResponseEntity;

import com.citius.vitalsignms.service.VitalSignsService;
import com.model.PatientDetailsDto;
import com.model.UserDto;
import com.model.VitalSignsDto;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VitalSignsControllerTest {

	@Mock
	private VitalSignsService vitalService;

	@InjectMocks
	VitalSignsController vitalController;

	@Test
	public void procedure_getallMethod_success() throws Exception {
		
		PatientDetailsDto patdto = new PatientDetailsDto();
		patdto.setId(1);
		UserDto userdto = new UserDto();
		userdto.setId(1);
		List<VitalSignsDto> signsdto = Arrays.asList(
				new VitalSignsDto(1, "h1", "w1", "bp1", "bt1", "rr1", "dt1", patdto, userdto, "meet1"),
				new VitalSignsDto(2, "h2", "w2", "bp2", "bt2", "rr2", "dt2", patdto, userdto, "meet2"));

		when(vitalService.listAll()).thenReturn(signsdto);

		ResponseEntity<List<VitalSignsDto>> actualList = vitalController.list();

		assertThat(signsdto, equalTo(actualList.getBody()));

	}

}
