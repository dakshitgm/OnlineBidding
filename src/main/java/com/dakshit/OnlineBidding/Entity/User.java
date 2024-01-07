package com.dakshit.OnlineBidding.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "user name should be blank")
    @Column(unique = true)
    private String userName;

    @NotBlank(message = "email id should be blank")
    @Column(unique = true)
    private String emailId;

    @NotBlank(message = "password should be blank")
    private String password;

    public User(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
