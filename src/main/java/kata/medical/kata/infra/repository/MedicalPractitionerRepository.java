package kata.medical.kata.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kata.medical.kata.infra.entities.MedicalPractitioner;

public interface MedicalPractitionerRepository extends JpaRepository<MedicalPractitioner, Long> {

}
