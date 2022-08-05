package com.cspl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User extends AuditEntity {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String mobileNumber;
    private String status;
    private LocalDateTime lastStatusUpdatedDate;
    private LocalDateTime lastVisitedDate;
    private String lastVisitedIp;
    private String password;

    public User(String userId, String mobileNumber, String status, String lastVisitedIp) {
        this.userId = userId;
        this.mobileNumber = mobileNumber;
        this.status = status;
        this.lastStatusUpdatedDate = LocalDateTime.now();
        this.lastVisitedDate = LocalDateTime.now();
        this.lastVisitedIp = lastVisitedIp;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public User setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public User setStatus(String status) {
        this.status = status;
        return this;
    }

    public void setLastStatusUpdatedDate(LocalDateTime lastStatusUpdatedDate) {
        this.lastStatusUpdatedDate = lastStatusUpdatedDate;
    }

    public User setLastVisitedDate(LocalDateTime lastVisitedDate) {
        this.lastVisitedDate = lastVisitedDate;
        return this;
    }

    public User setLastVisitedIp(String lastVisitedIp) {
        this.lastVisitedIp = lastVisitedIp;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
