package org.romanchi.model.dto;

import org.romanchi.model.User;

public class UserToRegisterDTO {
    private String userPassword;
    private long userId;
    private String username;
    private String userSurname;
    private String userLanguage;

    public UserToRegisterDTO() {
    }

    public UserToRegisterDTO(String userPassword, long userId, String username, String userSurname, String userLanguage) {
        this.userPassword = userPassword;
        this.userId = userId;
        this.username = username;
        this.userSurname = userSurname;
        this.userLanguage = userLanguage;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public User toEntity(){
        User user = new User();
        user.setId(userId);user.setUsername(username);
        user.setPassword(userPassword);
        return user;
    }
}
