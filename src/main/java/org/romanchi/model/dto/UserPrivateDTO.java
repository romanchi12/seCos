package org.romanchi.model.dto;


import org.romanchi.model.User;

public class UserPrivateDTO extends UserDTO{
    private String userPassword;

    UserPrivateDTO(){
        super();
    }

    UserPrivateDTO(User user){
        super(user);
        this.userPassword = user.getPassword();
    }

    @Override
    public User toEntity(){
        User user = super.toEntity();
        user.setPassword(userPassword);
        return user;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserPrivateDTO{" +
                "userPassword='" + userPassword + '\'' +
                '}' + super.toString();
    }
}
