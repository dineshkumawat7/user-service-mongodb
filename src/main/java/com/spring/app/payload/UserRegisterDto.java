package com.spring.app.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class UserRegisterDto {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(
            regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s'-]+$",
            message = "Name can only contain letters, spaces, hyphens, and apostrophes"
    )
    private String firstName;

    @Pattern(
            regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s'-]+$",
            message = "Name can only contain letters, spaces, hyphens, and apostrophes"
    )
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Pattern(
            regexp = "^(?!\\.)[a-zA-Z0-9._%+-]{1,64}(?<!\\.)@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,63}$",
            message = "Invalid email format"
    )
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^\\+[1-9]\\d{1,3}[0-9]{10,15}$",
            message = "Invalid mobile number format"
    )
    private String mobileNumber;

    @AssertTrue(message = "You must agree to the terms and conditions")
    @JsonProperty("isConsent")
    private boolean isConsent;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String firstName, String lastName, String email, String mobileNumber, boolean isConsent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.isConsent = isConsent;
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

    @JsonProperty("isConsent")
    public boolean isConsent() {return isConsent;}

    public void setConsent(boolean consent) {isConsent = consent;}
}
