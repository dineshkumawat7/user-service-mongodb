package com.spring.app.service.impl;

import com.spring.app.entity.User;
import com.spring.app.exception.PhoneAlreadyExistsException;
import com.spring.app.exception.ResourceNotFoundException;
import com.spring.app.exception.UnsupportedDtoClassException;
import com.spring.app.exception.UserAlreadyExistsException;
import com.spring.app.payload.UserRegisterDto;
import com.spring.app.payload.UserUpdateDto;
import com.spring.app.repositoty.UserRepo;
import com.spring.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String USERS_CACHE = "users";

    @Autowired
    private UserRepo userRepo;

//    @CachePut(value = USERS_CACHE, key = "#userDto.id")
    @Override
    public User registerOrUpdateUser(Object userDto) {
        if (userDto instanceof UserRegisterDto userRegisterDto) {
            validateUserUniqueness(userRegisterDto.getEmail(), userRegisterDto.getMobileNumber(), null);
            User user = new User();
            user.setId(UUID.randomUUID().toString().replace("-", ""));
            user.setFirstName(userRegisterDto.getFirstName());
            user.setLastName(userRegisterDto.getLastName());
            user.setEmail(userRegisterDto.getEmail().toLowerCase());
            user.setMobileNumber(userRegisterDto.getMobileNumber());
            user.setPassword("********");
            user.setConsent(userRegisterDto.isConsent());
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            User createdUser = userRepo.save(user);
            createdUser.setPassword("********");
            log.info("New user registered successfully: {}", createdUser);
            return createdUser;
        } else if (userDto instanceof UserUpdateDto userUpdateDto) {
            Optional<User> optionalUser = userRepo.findById(userUpdateDto.getId());
            if (optionalUser.isEmpty()) {
                log.error("Failed to update user: User not found with ID {}", userUpdateDto.getId());
                throw new ResourceNotFoundException("User not found with ID " + userUpdateDto.getId());
            }
            validateUserUniqueness(userUpdateDto.getEmail(), userUpdateDto.getMobileNumber(), userUpdateDto.getId());
            User existingUser = optionalUser.get();
            existingUser.setFirstName(userUpdateDto.getFirstName());
            existingUser.setLastName(userUpdateDto.getLastName());
            existingUser.setEmail(userUpdateDto.getEmail());
            existingUser.setMobileNumber(userUpdateDto.getMobileNumber());
            existingUser.setGender(userUpdateDto.getGender());
            existingUser.setProfilePictureUrl(userUpdateDto.getProfilePictureUrl());
            existingUser.setDateOfBirth(userUpdateDto.getDateOfBirth());
            existingUser.setUpdatedAt(LocalDateTime.now());
            User updatedUser = userRepo.save(existingUser);
            updatedUser.setPassword("********");
            log.info("User updated successfully: {}", updatedUser);
            return updatedUser;
        } else {
            log.error("Unsupported DTO class: {}", userDto.getClass().getName());
            throw new UnsupportedDtoClassException("Unsupported DTO class: " + userDto.getClass().getName());
        }
    }

    private void validateUserUniqueness(String email, String mobileNumber, String userId) {
        userRepo.findByEmail(email).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(userId)) {
                log.error("Email {} is already registered", email);
                throw new UserAlreadyExistsException("An user is already registered with email " + email);
            }
        });

        userRepo.findByMobileNumber(mobileNumber).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(userId)) {
                log.error("mobile number {} is already registered", mobileNumber);
                throw new PhoneAlreadyExistsException("An user is already registered with mobile number " + mobileNumber);
            }
        });
    }

    @Cacheable(value = USERS_CACHE, key = "'page_' + #pageNumber + '_size_' + #pageSize")
    @Override
    public Page<User> findPaginatedUsers(int pageNumber, int pageSize) {
        if (pageNumber < 0 || pageSize <= 0) {
            log.error("Invalid pagination parameters: pageNumber={}, pageSize={}", pageNumber, pageSize);
            throw new IllegalArgumentException("Page number must be >= 0 and page size must be > 0");
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepo.findAll(pageable);
        if (userPage.isEmpty()) {
            log.warn("No users found for pageNumber={} and pageSize={}", pageNumber, pageSize);
        } else {
            log.info("Paginated users retrieved successfully for pageNumber={} and pageSize={}", pageNumber, pageSize);
        }
        return userPage;
    }


    @Cacheable(value = USERS_CACHE, key = "#id")
    @Override
    public User findUserById(String id) {
        return userRepo.findById(id).orElseThrow(() -> {
            log.error("User not found with ID: {}", id);
            throw new ResourceNotFoundException("User not found with ID " + id);
        });
    }

    @CacheEvict(value = USERS_CACHE, key = "#id")
    @Override
    public void deleteUserById(String id) {
        userRepo.findById(id).ifPresentOrElse(
                user -> {
                    userRepo.delete(user);
                    log.info("User deleted successfully: {}", user);
                },
                () -> {
                    log.error("Failed to delete user: User not found with ID {}", id);
                    throw new ResourceNotFoundException("User not found with ID " + id);
                }
        );
    }
}
