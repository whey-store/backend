package begin.flywayspringmaven.api.file;

import begin.flywayspringmaven.api.file.dto.FileDTO;
import begin.flywayspringmaven.common.response.APIResponse;
import begin.flywayspringmaven.common.service.aws.FileService;
import begin.flywayspringmaven.exception.FileDownloadException;
import begin.flywayspringmaven.exception.FileEmptyException;
import begin.flywayspringmaven.exception.FileUploadException;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api/v1/file")
@Validated
public class FileUploadController {
    private final FileService fileService;

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws FileEmptyException, FileUploadException, IOException {
        if (multipartFile.isEmpty()){
            throw new FileEmptyException("File is empty. Cannot save an empty file");
        }
        boolean isValidFile = isValidFile(multipartFile);
        List<String> allowedFileExtensions = new ArrayList<>(Arrays.asList("pdf", "txt", "epub", "csv", "png", "jpg", "jpeg", "srt"));

        if (isValidFile){
            String fileName = fileService.uploadFile(multipartFile);
            FileDTO apiResponse = new FileDTO(
                "file uploaded successfully. File unique name =>" + fileName,
                true,
                200
            );
            return APIResponse.okStatus(apiResponse);
        } else {
            FileDTO apiResponse = new FileDTO(
                "Invalid File. File extension or File name is not supported",
                false,
                400
            );
            return APIResponse.okStatus(apiResponse);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam("fileName")  @NotBlank @NotNull String fileName) throws FileDownloadException, IOException {
        Object response = fileService.downloadFile(fileName);
        if (response != null){
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(response);
        } else {
            FileDTO apiResponse = new FileDTO(
                "File could not be downloaded",
                false,
                400
            );
            return APIResponse.okStatus(apiResponse);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("fileName") @NotBlank @NotNull String fileName){
        boolean isDeleted = fileService.delete(fileName);
        if (isDeleted){
            FileDTO apiResponse = new FileDTO(
                "file deleted!",
                true,
                200
            );
            return APIResponse.okStatus(apiResponse);
        } else {
            FileDTO apiResponse = new FileDTO(
                "file does not exist",
                false,
                404
            );
            return APIResponse.okStatus(apiResponse);
        }
    }

    private boolean isValidFile(MultipartFile multipartFile){
        log.info("Empty Status ==> {}", multipartFile.isEmpty());
        if (Objects.isNull(multipartFile.getOriginalFilename())){
            return false;
        }
        return !multipartFile.getOriginalFilename().trim().equals("");
    }
}
