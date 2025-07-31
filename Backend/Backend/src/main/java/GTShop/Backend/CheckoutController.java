    package GTShop.Backend;

    import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RestController;


    @RestController
    public class CheckoutController {

        @Autowired
        private UserProfile userProfile;

        @Autowired
        private CartService request;

        @PostMapping("/api/checkout")
        public ResponseEntity<?> postCheckout() {
        
            Cart cart=request.getCart();
            Integer totalPoints=cart.getTotalPoints();
            Integer userActivityPoints=userProfile.getActivityPoints();

            if(userActivityPoints<totalPoints){
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Map.of("success",false,"message","You dont have enough Activity Points!"));
            }
                Map<String,Object> response= new LinkedHashMap<>();
                List<CartItem> purchasedItems=new ArrayList<>(cart.getCartItems());

                cart.deleteItems();

                response.put("success", true);
                response.put("message", "Your purchase was confirmed!");

                userActivityPoints-=totalPoints;
                userProfile.setActivityPoints(userActivityPoints);
                response.put("newBalance", userActivityPoints);
                response.put("purchasedItems", purchasedItems);
                response.put("transactionId", "1");

                return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        
        }
        
    }
