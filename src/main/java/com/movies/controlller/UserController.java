package com.movies.controlller;

import com.movies.entity.dao.UploadFileResponse;
import com.movies.entity.dao.User;
import com.movies.service.FileStorageService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserService userService;

    @PostMapping("/upload-avatar")
    public UploadFileResponse uploadFile(Principal principal, @RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        User user = userService.findOneByUsername(principal.getName());
        user.setAvatar(fileName);
        userService.save(user);
        return new UploadFileResponse(fileName, file.getContentType(), file.getSize(), "SUCCESS");
    }
}
