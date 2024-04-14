package begin.flywayspringmaven.api.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class FileDTO {
    private String message;
    private boolean isSuccessful;
    private int statusCode;
}
