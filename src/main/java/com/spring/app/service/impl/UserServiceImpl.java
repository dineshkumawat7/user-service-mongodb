package com.spring.app.service.impl;

import com.spring.app.entity.User;
import com.spring.app.exception.PhoneAlreadyExistsException;
import com.spring.app.exception.ResourceNotFoundException;
import com.spring.app.exception.UserAlreadyExistsException;
import com.spring.app.payload.UserRegisterDto;
import com.spring.app.repositoty.UserRepo;
import com.spring.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(UserRegisterDto userRegisterDto) {
        if(userRepo.findByEmail(userRegisterDto.getEmail()).isPresent()){
            log.info("an user already register with email: {}", userRegisterDto.getEmail());
            throw new UserAlreadyExistsException("an user already register with email " + userRegisterDto.getEmail());
        }
        if(userRepo.findByPhone(userRegisterDto.getPhone()).isPresent()){
            log.info("an user already register with phone number {}", userRegisterDto.getPhone());
            throw new PhoneAlreadyExistsException("an user already register with phone number " + userRegisterDto.getEmail());
        }
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPhone(userRegisterDto.getPhone());
        user.setImgUrl(null);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setValidUser(false);
        user.setValidEmail(false);
        user.setValidPhone(false);
        User createdUser = userRepo.save(user);
        log.info("new user register successfully ::: {} :::", createdUser);
        return createdUser;
    }

    @Override
    public Page<User> findPaginatedUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepo.findAll(pageable);
    }

    @Override
    public User findUserById(String id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            log.info("user found successfully ::: {} :::", optionalUser.get());
            return optionalUser.get();
        }
        log.error("user not found with id: {}", id);
        throw new ResourceNotFoundException("user not found with id " + id);
    }

    @Override
    public void deleteUserById(String id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()){
            userRepo.delete(optionalUser.get());
            log.info("user deleted successfully ::: {} :::", optionalUser.get());
            return;
        }
        log.error("failed to delete user because wrong user id : {}", id);
        throw new ResourceNotFoundException("user not found with id " + id);
    }
}
