package begin.flywayspringmaven.api.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class FileDTO {
    private String url;
    private boolean isSuccessful;
    private int statusCode;
}
