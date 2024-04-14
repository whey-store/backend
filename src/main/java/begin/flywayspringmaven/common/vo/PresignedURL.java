package begin.flywayspringmaven.common.vo;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class PresignedURL {
    private URL preSignedURL;
    private String url;
    private String privatePreSignedURL;
}
