package begin.flywayspringmaven.support.httpdefault.converter;

public class DefaultFieldConverter implements FieldConverter<Object, Object>{

    @Override
    public Object convertFieldEntityToDTO(Object entityField) {
        return entityField;
    }

    @Override
    public Object convertFieldDTOToEntity(Object dtoField) {
        return dtoField;
    }
}
