package com.spring.app.service;

import com.spring.app.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User registerOrUpdateUser(Object userDto);

    Page<User> findPaginatedUsers(int pageNumber, int pageSize);

    User findUserById(String id);

    void deleteUserById(String id);
}
