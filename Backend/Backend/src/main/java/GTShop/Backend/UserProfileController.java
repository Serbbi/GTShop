package GTShop.Backend;


import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserProfile userProfile;

    @GetMapping("/profile")
    public UserProfile getProfile(){
        return userProfile;
    }

    @GetMapping("/history")
    public Map<String,Object> getPurchaseHistory(){

        Map<String,Object> response=new LinkedHashMap<>();
        response.put("purchases",userProfile.getUserHistory());
        return response;
    }
    
}
