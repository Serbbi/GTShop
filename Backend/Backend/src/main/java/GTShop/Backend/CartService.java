package GTShop.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private Cart cart;

    public Cart getCart(){
        return cart;
    }

    public void addToCart(Reward reward, Integer quantity){

        cart.addItem(reward, quantity);
    }

}
