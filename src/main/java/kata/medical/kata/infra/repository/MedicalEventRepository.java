package kata.medical.kata.infra.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kata.medical.kata.infra.entities.MedicalEvent;
import kata.medical.kata.infra.entities.MedicalEventType;

public interface MedicalEventRepository extends JpaRepository<MedicalEvent, Long> {
    Optional<MedicalEvent> findTop1ByEventTypeAndPractitionerIdAndStartDateTimeBetweenOrderByStartDateTimeAsc(
            MedicalEventType type,
            Long practitionerId,
            LocalDateTime start,
            LocalDateTime end);

    List<MedicalEvent> findByEventTypeAndPractitionerIdAndStartDateTimeBetween(
        MedicalEventType eventType,
        Long practitionerId,
        LocalDateTime start,
        LocalDateTime end
    );

    Optional<MedicalEvent> findFirstByEventTypeAndPractitionerIdAndPatientIdAndStartDateTimeBetweenOrderByStartDateTimeAsc(
        MedicalEventType eventType,
        Long practitionerId,
        Long patientId,
        LocalDateTime start,
        LocalDateTime end
    );
}
