package bg.DeveloperGroup.musicdb.models.binding;

public class UserChangeProfileBindingModel {

    private String username;

    public UserChangeProfileBindingModel(){
    }

    public String getUsername(){
        return username;
    }

    public UserChangeProfileBindingModel setUsername(String username){
        this.username = username;
        return this;
    }
}
