package GTShop.Backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserProfile userProfile;

    // @Autowired
    // private CartService cartService;

    @GetMapping("/profile")
    public UserProfile getProfile(){
        return userProfile;
    }

    // @GetMapping("/history")
    // public Map<String,Object> purchaseHistory(){

    //     Map<String,Object> response=new LinkedHashMap<>();
    //     response.put("purchases", cartService.getCart().getCartItems());

    //     return response;
    // }
    
}
