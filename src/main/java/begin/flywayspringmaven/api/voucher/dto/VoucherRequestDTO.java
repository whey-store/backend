package begin.flywayspringmaven.api.voucher.dto;

import begin.flywayspringmaven.support.httpdefault.MapField;
import begin.flywayspringmaven.support.httpdefault.converter.DateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class VoucherRequestDTO {
    private String name;
    private Float percentReduction;
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @MapField(converter = DateTimeConverter.class)
    private LocalDateTime startDay;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @MapField(converter = DateTimeConverter.class)
    private LocalDateTime endDay;

    private Integer storeId;
}
