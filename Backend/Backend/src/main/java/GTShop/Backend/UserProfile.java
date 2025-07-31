package GTShop.Backend;

import org.springframework.stereotype.Service;

@Service
public class UserProfile {

    private String id;
    private String name;
    private String avatar;
    private Integer activityPoints;

    public UserProfile(){
        this.id="1111";
        this.name="Briana";
        this.avatar="image.pnm";
        this.activityPoints=4971;
    }

    public UserProfile(String id, String name, String avatar, Integer activityPoints) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.activityPoints = activityPoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getActivityPoints() {
        return activityPoints;
    }

    public void setActivityPoints(Integer activityPoints) {
        this.activityPoints = activityPoints;
    }
}
