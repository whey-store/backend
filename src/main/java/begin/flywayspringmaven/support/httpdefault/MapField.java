package begin.flywayspringmaven.support.httpdefault;

import begin.flywayspringmaven.support.httpdefault.converter.DefaultFieldConverter;
import begin.flywayspringmaven.support.httpdefault.converter.FieldConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MapField {
    /**
     * Defined field path of DTO.
     *
     * @return field path
     */
    String from() default "";

    /**
     * Defined field path of Entity.
     *
     * @return field path
     */
    String mapTo() default "";

    /**
     * Defined special value, for example Id of login user.
     *
     * @return key of special value
     */
    SpecialValue specialFrom() default SpecialValue.NONE;


    /**
     * Defined converter for field between DTO and Entity.
     *
     * @return the converter
     */
    Class<? extends FieldConverter<?, ?>> converter() default DefaultFieldConverter.class;
}
