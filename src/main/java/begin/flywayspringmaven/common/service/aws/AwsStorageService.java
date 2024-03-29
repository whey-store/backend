package begin.flywayspringmaven.common.service.aws;

import begin.flywayspringmaven.common.vo.PresignedURL;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

@Configuration
@Profile({"local"})
public class AwsStorageService implements IAwsService{

    @Autowired
    private AmazonS3 s3Client;

    @Value("${cloud.aws.bucket-name}")
    private String bucketName;

    @Value("${app.pre-signed-expired}")
    private int preSignedExpired;

    public PresignedURL uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return this.uploadPreSignedUrl(fileName);
    }

    /**
     * Create pre signed url for PUT method
     *
     * @param fileName fileName
     * @return pre sign url
     */
    @Override
    public PresignedURL uploadPreSignedUrl(String fileName) {
        PresignedURL preSignedAWS = new PresignedURL();
        Date date = new Date();
        date = DateUtils.addMinutes(date, preSignedExpired);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(this.bucketName, fileName);
        generatePresignedUrlRequest.setMethod(HttpMethod.PUT);
        generatePresignedUrlRequest.setExpiration(date);
        URL url = this.s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        preSignedAWS.setPreSignedURL(url);
        String urlRes = url.getProtocol() + "://" + url.getPath();
        preSignedAWS.setUrl(urlRes);
        return preSignedAWS;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
        }
        return convertedFile;
    }
}