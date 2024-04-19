package begin.flywayspringmaven.common.service.aws;

import begin.flywayspringmaven.exception.FileDownloadException;
import begin.flywayspringmaven.exception.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

public interface FileService {
    URL uploadFile(MultipartFile multipartFile) throws FileUploadException, IOException;

    Object downloadFile(String fileName) throws FileDownloadException, IOException;

    boolean delete(String fileName);
}
