package begin.flywayspringmaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(
    exclude = {
        org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
    }
)
@EnableSwagger2
public class FlywaySpringMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywaySpringMavenApplication.class, args);
    }

}
