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
        for (CartItem item : cart.getCartItems()) {
            if (item.getReward().getId().equals(reward.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        CartItem newItem = new CartItem(reward, quantity);
        cart.getCartItems().add(newItem);
    }

    public void removeFromCart(Reward reward){

        CartItem cartItemToRemove = null;
        for (CartItem item : cart.getCartItems()) {
            if (item.getReward().getId().equals(reward.getId())) {
                item.setQuantity(item.getQuantity() -1);
                if(item.getQuantity() == 0 ){
                    cartItemToRemove = item;
                }
                break;
            }
        }
        if(cartItemToRemove!=null){
            cart.removeItem(cartItemToRemove);
        }
    }

}
