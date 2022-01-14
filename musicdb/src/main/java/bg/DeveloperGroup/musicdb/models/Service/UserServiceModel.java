package bg.DeveloperGroup.musicdb.models.Service;

import bg.DeveloperGroup.musicdb.models.entity.UserRoleEntity;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {

    private String username;
    private String password;
    private String fullname;
    private List<UserRoleEntity> roles = new ArrayList<> ();

    public UserServiceModel(){
    }

    public String getUsername(){
        return username;
    }

    public UserServiceModel setUsername(String username){
        this.username = username;
        return this;
    }

    public String getPassword(){
        return password;
    }

    public UserServiceModel setPassword(String password){
        this.password = password;
        return this;
    }

    public String getFullname(){
        return fullname;
    }

    public UserServiceModel setFullname(String fullname){
        this.fullname = fullname;
        return this;
    }

    public List<UserRoleEntity> getRoles(){
        return roles;
    }

    public UserServiceModel setRoles(List<UserRoleEntity> roles){
        this.roles = roles;
        return this;
    }
}
