package com.movies.controlller;

import com.movies.entity.dao.UploadFileResponse;
import com.movies.entity.dao.User;
import com.movies.entity.dto.UserDetailDto;
import com.movies.service.FileStorageService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/users")
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

    @GetMapping("/customers")
    public List<UserDetailDto> getAllCustomer() {
        return userService.getAllUsers("ROLE_CUSTOMER");
    }

    @GetMapping("/staffs")
    public List<UserDetailDto> getAllStaffs() {
        return userService.getAllUsers("ROLE_STAFF");
    }

    @GetMapping("/{userId}")
    public UserDetailDto getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserDetailDto updateUser(@PathVariable Integer userId, @Valid @RequestBody UserDetailDto userDetailDto) {
        userDetailDto.setId(userId);
        return userService.update(userDetailDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
    }
}
