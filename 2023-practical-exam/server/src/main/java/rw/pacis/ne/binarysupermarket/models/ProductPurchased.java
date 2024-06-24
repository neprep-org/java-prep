package rw.pacis.ne.binarysupermarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import rw.pacis.ne.binarysupermarket.audits.TimestampAudit;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_purchased")
public class ProductPurchased extends TimestampAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @Type(type="uuid-binary")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "quantity_id")
    private ProductQuantity productQuantity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @Column
    private Double total;

    public ProductPurchased(ProductQuantity productQuantity, User customer) {
        this.productQuantity = productQuantity;
        this.customer = customer;
    }
}
