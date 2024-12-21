package com.spring.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String imgUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isValidUser;
    private boolean isValidEmail;
    private boolean isValidPhone;

    public User() {
    }

    public User(String id, String lastName, String firstName, String email, String phone, String imgUrl, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isValidUser, boolean isValidEmail, boolean isValidPhone) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isValidUser = isValidUser;
        this.isValidEmail = isValidEmail;
        this.isValidPhone = isValidPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isValidUser() {
        return isValidUser;
    }

    public void setValidUser(boolean validUser) {
        isValidUser = validUser;
    }

    public boolean isValidEmail() {
        return isValidEmail;
    }

    public void setValidEmail(boolean validEmail) {
        isValidEmail = validEmail;
    }

    public boolean isValidPhone() {
        return isValidPhone;
    }

    public void setValidPhone(boolean validPhone) {
        isValidPhone = validPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isValidUser=" + isValidUser +
                ", isValidEmail=" + isValidEmail +
                ", isValidPhone=" + isValidPhone +
                '}';
    }
}
