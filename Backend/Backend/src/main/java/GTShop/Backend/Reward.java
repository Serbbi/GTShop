package GTShop.Backend;

import jakarta.persistence.*;

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String description;
    private Integer price;
    private String image;
    private String category;
    private boolean inStock;
    private Integer stockCount;

    public Reward(){
        
    }

    public Reward(String name, String description, Integer price, String image, String category, boolean inStock, Integer stockCount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
        this.inStock = inStock;
        this.stockCount = stockCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
