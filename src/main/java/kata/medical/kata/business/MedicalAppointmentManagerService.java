package kata.medical.kata.business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kata.medical.kata.business.exceptions.NotFoundEntity;
import kata.medical.kata.business.exceptions.PatientOverBookedPractitionerException;
import kata.medical.kata.infra.entities.MedicalEvent;
import kata.medical.kata.infra.entities.MedicalEventType;
import kata.medical.kata.infra.entities.MedicalPatient;
import kata.medical.kata.infra.entities.MedicalPractitioner;
import kata.medical.kata.infra.repository.MedicalEventRepository;
import kata.medical.kata.infra.repository.MedicalPatientRepository;
import kata.medical.kata.infra.repository.MedicalPractitionerRepository;

@Service()
public class MedicalAppointmentManagerService {
    private final MedicalEventRepository medicalEventRepository;
    private final MedicalPatientRepository medicalPatientRepository;
    private final MedicalPractitionerRepository medicalPractitionerRepository;

    @Autowired
    public MedicalAppointmentManagerService(
            MedicalEventRepository medicalEventRepository,
            MedicalPatientRepository medicalPatientRepository,
            MedicalPractitionerRepository medicalPractitionerRepository) {
        this.medicalEventRepository = medicalEventRepository;
        this.medicalPatientRepository = medicalPatientRepository;
        this.medicalPractitionerRepository = medicalPractitionerRepository;
    }

    @Transactional
    List<MedicalEvent> getAvailableEventsWithinWeekForPractitionerIdOnDate(
            Long practitionerId,
            LocalDate date) {
        LocalDateTime start = LocalDateTime.from(date);
        LocalDateTime end = start.plusDays(7);
        return medicalEventRepository.findByEventTypeAndPractitionerIdAndStartDateTimeBetween(
                MedicalEventType.OPENING, practitionerId, start, end);
    }

    @Transactional
    MedicalEvent bookMedicalEvent(Long patientId, Long eventId) {
        MedicalEvent event = medicalEventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundEntity("can't find practitioner with the event"));
        if (Objects.equals(event.getEventType(), MedicalEventType.APPOINTMENT)) {
            throw new IllegalArgumentException("event already booked");
        }
        MedicalPatient patient = medicalPatientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundEntity("can't find patient to book an event"));

        this.checkEventAllowedForPatient(patient, event);

        return null;
    }

    public void checkEventAllowedForPatient(MedicalPatient patient, MedicalEvent event) {
        // check event OverBooked
        MedicalPractitioner practitioner = medicalPractitionerRepository.findById(event.getPractitioner().getId())
                .orElseThrow(() -> new NotFoundEntity("can't find practitioner on the event"));

        checkOverBookedAroundDate(practitioner, patient, event.getStartDateTime());
    }

    public void checkOverBookedAroundDate(MedicalPractitioner practitioner, MedicalPatient patient,
            LocalDateTime date) {
        if (practitioner.isAllowOverBooking()) {
            return;
        }

        medicalEventRepository
                .findFirstByEventTypeAndPractitionerIdAndPatientIdAndStartDateTimeBetweenOrderByStartDateTimeAsc(
                        MedicalEventType.APPOINTMENT,
                        practitioner.getId(),
                        patient.getId(),
                        date,
                        date.plusDays(7))
                        .ifPresent(overBookedAppointment -> {
                            throw new PatientOverBookedPractitionerException(practitioner,patient,date,overBookedAppointment);
                        });
    }
}
