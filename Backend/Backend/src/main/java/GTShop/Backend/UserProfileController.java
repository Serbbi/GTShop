package GTShop.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @Autowired
    private UserProfile userProfile;

    @GetMapping("api/user/profile")
    public UserProfile getProfile(){
        return userProfile;
    }
    
}
