package org.romanchi.model.dto;


import org.romanchi.model.User;

public class UserDTO {
    private long userId;
    private String username;
    private String photo;

    UserDTO() {
    }

    public UserDTO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.photo = user.getPhotoFile();
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User toEntity() {
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setPhotoFile(this.photo);
        return user;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
