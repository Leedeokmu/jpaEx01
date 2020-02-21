import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id @GeneratedValue
    @Column(name="ITEM_ID")
    private Long id;
    private Integer price;
    private Integer stockquantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStockquantity() {
        return stockquantity;
    }

    public void setStockquantity(Integer stockquantity) {
        this.stockquantity = stockquantity;
    }
}
