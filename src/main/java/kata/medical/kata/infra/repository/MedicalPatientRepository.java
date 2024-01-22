package kata.medical.kata.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kata.medical.kata.infra.entities.MedicalPatient;

public interface MedicalPatientRepository extends JpaRepository<MedicalPatient, Long> {

}
