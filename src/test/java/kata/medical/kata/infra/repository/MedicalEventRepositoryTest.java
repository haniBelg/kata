package kata.medical.kata.infra.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import kata.medical.kata.business.MedicalAppointmentManagerService;
import kata.medical.kata.business.exceptions.PatientOverBookedPractitionerException;
import kata.medical.kata.infra.entities.MedicalPatient;
import kata.medical.kata.infra.entities.MedicalPractitioner;

// @DataJpaTest
@SpringBootTest
public class MedicalEventRepositoryTest {
	@Autowired
	MedicalEventRepository medicalEventRepository;
	@Autowired
	MedicalPractitionerRepository medicalPractitionerRepository;
	@Autowired
	MedicalPatientRepository medicalPatientRepository;

	@Autowired
	MedicalAppointmentManagerService medicalAppointmentManagerService;

	@Test
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, value = "/data-test.sql")
	void checkOverBookedAroundDate() {
		MedicalPatient patient = medicalPatientRepository.findById(2L).get();
		MedicalPractitioner practitioner = medicalPractitionerRepository.findById(2L).get();
		LocalDateTime dateTime = LocalDateTime.parse("2024-01-28T08:00:00");

		PatientOverBookedPractitionerException exception = assertThrows(
				PatientOverBookedPractitionerException.class,
				() -> {
					medicalAppointmentManagerService.checkOverBookedAroundDate(practitioner, patient, dateTime);
				});
		assertEquals(exception.getOverBookedMedicalEvent().getId(), 5);
	}
}
