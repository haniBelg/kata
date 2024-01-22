package kata.medical.kata.business.exceptions;

import java.time.LocalDateTime;

import kata.medical.kata.infra.entities.MedicalEvent;
import kata.medical.kata.infra.entities.MedicalPatient;
import kata.medical.kata.infra.entities.MedicalPractitioner;
import lombok.Getter;

@Getter
public class PatientOverBookedPractitionerException extends RuntimeException {
    private final MedicalPractitioner practitioner;
    private final MedicalPatient patient;
    private final LocalDateTime date;
    private final MedicalEvent overBookedMedicalEvent;

    public PatientOverBookedPractitionerException(
            MedicalPractitioner practitioner,
            MedicalPatient patient,
            LocalDateTime date,
            MedicalEvent medicalEvent) {
        super("[PatientOverBookedPractitioner] - " +
                practitioner.getId() +
                patient.getId() +
                date.toString() +
                medicalEvent.getId());
        this.practitioner = practitioner;
        this.patient = patient;
        this.date = date;
        this.overBookedMedicalEvent = medicalEvent;
    }
}
