package begin.flywayspringmaven.support.httpdefault.converter;

import begin.flywayspringmaven.util.FlywaySpringUtils;

import java.time.LocalDateTime;

public class DateTimeConverter implements FieldConverter<LocalDateTime, Long>{
    @Override
    public Long convertFieldEntityToDTO(LocalDateTime entityField) {
        if (null != entityField) {
            return FlywaySpringUtils.convertLocalDateTimeToMilli(entityField);
        }
        return 0L;
    }

    @Override
    public LocalDateTime convertFieldDTOToEntity(Long dtoField) {
        if (null != dtoField) {
            return FlywaySpringUtils.convertMilliToLocalDateTime(dtoField);
        }
        return null;
    }
}
