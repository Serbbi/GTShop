package GTShop.Backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class Cart {

    private List<CartItem> items=new ArrayList<>();
    private Integer totalPoints;

    public Cart(){
        
        this.totalPoints=0;
    }

    public void addItem(Reward reward, Integer quantity){
        items.add(new CartItem(reward,quantity));
    }

    public List<CartItem> getCartItems(){
        return items;
    }

    public Integer getTotalPoints(){
        
        int s=0;
        for(var i:items){
            s+=i.getQuantity()*i.getReward().getPrice();
        }
        this.totalPoints=s;
        return this.totalPoints;
    }

}
