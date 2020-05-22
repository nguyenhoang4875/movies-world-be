package com.movies.controlller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @GetMapping(value = "/{name}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getFile(@PathVariable String name) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("movies/uploads/" + name);
        return ResponseEntity.ok().body(IOUtils.toByteArray(in));
    }
}
