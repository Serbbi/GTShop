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

    public void addToCart(Reward reward, int quantity) {
        Cart cart = getCart();

        for (CartItem item : cart.getCartItems()) {
            if (item.getReward().getId().equals(reward.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        CartItem newItem = new CartItem(reward, quantity);
        cart.getCartItems().add(newItem);
    }

}
