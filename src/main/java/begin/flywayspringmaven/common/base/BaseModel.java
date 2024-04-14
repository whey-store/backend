package begin.flywayspringmaven.common.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import begin.flywayspringmaven.support.httpdefault.MapField;
import begin.flywayspringmaven.support.httpdefault.converter.DateTimeConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
/**
 * Base Model
 */
@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @CreationTimestamp
    @JsonIgnore
    @MapField(converter = DateTimeConverter.class)
    public LocalDateTime created;

    @UpdateTimestamp
    @JsonIgnore
    @MapField(converter = DateTimeConverter.class)
    public LocalDateTime modified;

}

