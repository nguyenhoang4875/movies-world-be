package com.movies.controlller;

import com.movies.entity.dao.UploadFileResponse;
import com.movies.entity.dao.User;
import com.movies.service.FileStorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping(value = "/{name}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getFile(@PathVariable String name) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("movies/uploads/" + name);
        return ResponseEntity.ok().body(IOUtils.toByteArray(in));
    }

    @PostMapping
    public UploadFileResponse uploadFile(Principal principal, @RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        return new UploadFileResponse(fileName, file.getContentType(), file.getSize(), "SUCCESS");
    }
}
