package Dominio.Utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {

        return Timestamp.valueOf(locDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlDate) {
        return sqlDate == null ? null : LocalDateTime.from(sqlDate.toLocalDateTime());
    }
}
