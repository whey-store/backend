package begin.flywayspringmaven.api.file;

import begin.flywayspringmaven.common.service.aws.AwsStorageService;
import begin.flywayspringmaven.common.vo.PresignedURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
public class UploadController {

    @Autowired
    private AwsStorageService awsStorageService;

    @PostMapping("/upload")
    public ResponseEntity<PresignedURL> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(awsStorageService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = awsStorageService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(awsStorageService.deleteFile(fileName), HttpStatus.OK);
    }
}
