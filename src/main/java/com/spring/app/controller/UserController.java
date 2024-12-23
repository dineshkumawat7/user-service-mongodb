package com.spring.app.controller;

import com.spring.app.entity.User;
import com.spring.app.payload.SuccessResponse;
import com.spring.app.payload.UserRegisterDto;
import com.spring.app.payload.UserUpdateDto;
import com.spring.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/status")
    public String getStatus() {
        return "Running up user service..";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        User createdUser = userService.registerOrUpdateUser(userRegisterDto);
        SuccessResponse<User> response = new SuccessResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("New user successfully created");
        response.setData(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getPaginatedUsers(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
        Page<User> users = userService.findPaginatedUsers(pageNumber, pageSize);
        SuccessResponse<Page<User>> response = new SuccessResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("users found successfully");
        response.setData(users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
        User user = userService.findUserById(id.trim());
        SuccessResponse<User> response = new SuccessResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("user found successfully");
        response.setData(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUserById(@PathVariable("id") String id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        userUpdateDto.setId(id);
        User updatedUser = userService.registerOrUpdateUser(userUpdateDto);
        SuccessResponse<User> response = new SuccessResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("user updated successfully");
        response.setData(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") String id) {
        userService.deleteUserById(id.trim());
        SuccessResponse<User> response = new SuccessResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("user deleted successfully");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
