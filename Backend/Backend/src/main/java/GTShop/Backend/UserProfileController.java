package GTShop.Backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @GetMapping("api/user/profile")
    public UserProfile getProfile(){
        return new UserProfile("1111","Briana", "image.png", 4971);
    }
    
}
