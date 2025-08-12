package GTShop.Backend;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest request){

        Reward reward=rewardRepository.findById(request.getRewardId()).orElse(null);

        if(reward==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("succes",false,"message","Reward not found!"));
        }

        cartService.addToCart(reward,request.getQuantity());

        Map<String,Object> response=new LinkedHashMap<>();
        response.put("succes", true);
        response.put("message", "Reward added to your cart!");
        response.put("cart", Map.of("items",cartService.getCart().getCartItems(),
                                        "totalPoints",cartService.getCart().getTotalPoints()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeFromCart(@RequestBody AddToCartRequest request){

        Reward reward=rewardRepository.findById(request.getRewardId()).orElse(null);

        if(reward==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("succes",false,"message","Reward not found!"));
        }

        cartService.removeFromCart(reward);

        Map<String,Object> response=new LinkedHashMap<>();
        response.put("succes", true);
        response.put("message", "Reward added to your cart!");
        response.put("cart", Map.of("items",cartService.getCart().getCartItems(),
                "totalPoints",cartService.getCart().getTotalPoints()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping()
    public Map<String,Object> getRealCart() {

        Map<String,Object> response= new LinkedHashMap<>();
        response.put("items", cartService.getCart().getCartItems());
        response.put("totalPoints", cartService.getCart().getTotalPoints());

        return response;
       
    }
    
}
