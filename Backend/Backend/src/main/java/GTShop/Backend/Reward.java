package GTShop.Backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Reward {

    @Id
    private String id;

    private String name;
    private String description;
    private String fullDescription;
    private Integer price;
    private String image;
    private String category;
    private boolean inStock;
    private Integer stockCount;

    public Reward(){
        
    }

    public Reward(String id, String name, String description, String fullDescription, Integer price, String image, String category, boolean inStock, Integer stockCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fullDescription = fullDescription;
        this.price = price;
        this.image = image;
        this.category = category;
        this.inStock = inStock;
        this.stockCount = stockCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }
}
