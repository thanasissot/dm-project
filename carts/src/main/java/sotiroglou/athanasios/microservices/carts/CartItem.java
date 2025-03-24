package sotiroglou.athanasios.microservices.carts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem )) return false;
        return id != null && id.equals(((CartItem) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}