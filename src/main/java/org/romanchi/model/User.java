package org.romanchi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserId")
    private Long id;

    @Column(name="UserName")
    private String username;

    @Column(name="UserPassword")
    private String password;

    @Column(name="UserEmail")
    private String useremail;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UserRoles", joinColumns = @JoinColumn(name = "USERID", referencedColumnName = "UserId"), inverseJoinColumns = @JoinColumn(name = "ROLEID", referencedColumnName = "RoleId"))
    private Set<Role> roles;


    @Column(name="Enabled")
    private boolean active;

    @Column(name="AccountLocked")
    private boolean accountLocked;

    @Column(name="CredentialsExpired")
    private boolean credentialsExprired;

    @Column(name="AccountExpired")
    private boolean accountExprired;

    @Column(name = "UserPhotoFile")
    private String photoFile;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name ="user_alergen", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "UserId"), inverseJoinColumns = @JoinColumn(name = "alergen_id", referencedColumnName = "alergen_item_id"))
    private Set<AlergenItem> alergenItems;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isCredentialsExprired() {
        return credentialsExprired;
    }

    public void setCredentialsExprired(boolean credentialsExprired) {
        this.credentialsExprired = credentialsExprired;
    }

    public boolean isAccountExprired() {
        return accountExprired;
    }

    public void setAccountExprired(boolean accountExprired) {
        this.accountExprired = accountExprired;
    }


    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Set<AlergenItem> getAlergenItems() {
        return alergenItems;
    }

    public void setAlergenItems(Set<AlergenItem> alergenItems) {
        this.alergenItems = alergenItems;
    }

    public String getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(String photoFile) {
        this.photoFile = photoFile;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        if(obj.getClass() != getClass()){
            return false;
        }

        User objUser = (User) obj;

        return objUser.getId().equals(getId());
    }

    @Override
    public int hashCode(){
        return 31 * ((getId()==null) ? 0:getId().hashCode());
    }
}
