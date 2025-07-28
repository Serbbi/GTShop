package GTShop.Backend;

public class CartItem {

    private Reward reward;
    private Integer quantity;

    public CartItem(Reward reward, Integer quantity) {
        this.reward = reward;
        this.quantity = quantity;
    }

    public Reward getReward() {
        return reward;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
