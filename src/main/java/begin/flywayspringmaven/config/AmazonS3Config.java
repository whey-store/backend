package begin.flywayspringmaven.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configurable
public class AmazonS3Config {

    @Value("${cloud.aws.credentials.access-key}")
    private String s3AccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String s3SecretKey;

    @Value("${cloud.aws.region.static}")
    private String s3Region;

    public AWSCredentialsProvider credential() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.s3AccessKey, this.s3SecretKey);
        return new AWSStaticCredentialsProvider(credentials);
    }

    @Bean
    public AmazonS3 s3Client() {
        return AmazonS3ClientBuilder.standard()
            .withCredentials(credential())
            .withRegion(s3Region)
            .build();
    }
}
