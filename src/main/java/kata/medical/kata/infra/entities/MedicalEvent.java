package kata.medical.kata.infra.entities;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "medical_events")
public class MedicalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    LocalDateTime startDateTime;
    @Convert(converter = DurationConverter.class)
    Duration duration;
    @ManyToOne(fetch = FetchType.LAZY)
    MedicalPractitioner practitioner;
    @ManyToOne(fetch = FetchType.LAZY)
    MedicalPatient patient;
    @Enumerated(EnumType.STRING)
    MedicalEventType eventType;
}
