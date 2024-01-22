package kata.medical.kata.infra.entities;

import java.time.Duration;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, String> {

    @Override
    public String convertToDatabaseColumn(Duration duration) {
        return duration.toString();
    }

    @Override
    public Duration convertToEntityAttribute(String durationString) {
        return Duration.parse(durationString);
    }
}
