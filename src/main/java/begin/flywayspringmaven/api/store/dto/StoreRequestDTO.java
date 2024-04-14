package begin.flywayspringmaven.api.store.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreRequestDTO {
    private Integer id;
    private String name;
    private String avatar;
    private String address;
    private  String description;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private String status;
    private LocalDateTime modified;
    private Integer userId;
}
