package Utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDateTime locDateTime) {
        return locDateTime == null ? null : java.sql.Date
                .valueOf(locDateTime.toLocalDate());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date sqlDate) {
        return sqlDate == null ? null : LocalDateTime.from(sqlDate.toLocalDate());
    }
}
