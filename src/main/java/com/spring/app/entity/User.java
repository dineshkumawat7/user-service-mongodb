package com.spring.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document(collection = "users")
public class User implements Serializable {
    @Id
    @JsonProperty("id")
    @Indexed(unique = true)
    private String id;

    @JsonProperty("firstName")
    @Field("firstName")
    private String firstName;

    @JsonProperty("lastName")
    @Field("lastName")
    private String lastName;

    @Indexed(unique = true)
    @JsonProperty("email")
    private String email;

    @Indexed(unique = true)
    @JsonProperty("mobileNumber")
    @Field("mobileNumber")
    private String mobileNumber;

    @JsonProperty("profilePictureUrl")
    private String profilePictureUrl;

    @JsonProperty("dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime dateOfBirth;

    @JsonProperty("gender")
    private String gender;

    @CreatedDate
    @JsonProperty("createdAt")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @JsonProperty("updatedAt")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;

    @JsonProperty("password")
    private String password;

    @JsonProperty("isValidUser")
    private boolean isValidUser;

    @JsonProperty("isEmailVerified")
    private boolean isEmailVerified;

    @JsonProperty("isPhoneVerified")
    private boolean isPhoneVerified;

    @JsonProperty("isConsent")
    private boolean isConsent;

    public User() {
    }

    public User(String id, String firstName, String lastName, String email, String mobileNumber, String profilePictureUrl,
                LocalDateTime dateOfBirth, String gender, LocalDateTime createdAt, LocalDateTime updatedAt, String password,
                boolean isValidUser, boolean isEmailVerified, boolean isPhoneVerified, boolean isConsent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.profilePictureUrl = profilePictureUrl;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.password = password;
        this.isValidUser = isValidUser;
        this.isEmailVerified = isEmailVerified;
        this.isPhoneVerified = isPhoneVerified;
        this.isConsent = isConsent;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("isValidUser")
    public boolean isValidUser() {
        return isValidUser;
    }

    public void setValidUser(boolean validUser) {
        isValidUser = validUser;
    }

    @JsonProperty("isEmailVerified")
    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    @JsonProperty("isPhoneVerified")
    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    @JsonProperty("isConsent")
    public boolean isConsent() {
        return isConsent;
    }

    public void setConsent(boolean consent) {
        isConsent = consent;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", password='" + password + '\'' +
                ", isValidUser=" + isValidUser +
                ", isEmailVerified=" + isEmailVerified +
                ", isPhoneVerified=" + isPhoneVerified +
                ", isConsent=" + isConsent +
                '}';
    }
}
