package com.spring.app.service;

import com.spring.app.entity.User;
import com.spring.app.payload.UserRegisterDto;
import org.springframework.data.domain.Page;

public interface UserService {
    User createUser(UserRegisterDto userRegisterDto);
    Page<User> findPaginatedUsers(int pageNumber, int pageSize);
    User findUserById(String id);
    void deleteUserById(String id);
}
