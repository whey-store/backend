package begin.flywayspringmaven.support.httpdefault.annotation.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CreateRequestClassDTO {
    /**
     * DTO Class for Create Request.
     *
     * @return class
     */
    Class<?> value();
}
