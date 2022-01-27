package com.citius.vitalsignms.repo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.citius.vitalsignms.entity.VitalSigns;


@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class VitalSignsRepoTest {

	@Autowired
	private VitalSignsRepo vitalRepository;

	@BeforeEach
	void initUseCase() {
	
		List<VitalSigns> signs = Arrays.asList(
				new VitalSigns( 1,"h1","w1","bp1","bt1","rr1","dt1","meet1",4,4),
				new VitalSigns( 2,"h2","w2","bp2","bt2","rr2","dt2","meet2",4,4));
		vitalRepository.saveAll(signs);
	}
	
	
	@AfterEach
	public void destroyAll() {
		vitalRepository.deleteAll();
	}
	
	@Test
	void findAll_success() {
		List<VitalSigns> allUser = vitalRepository.findAll();
		assertThat(allUser.size(), equalTo(2));
	
	}
}
