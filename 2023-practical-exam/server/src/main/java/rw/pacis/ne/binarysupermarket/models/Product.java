package rw.pacis.ne.binarysupermarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.pacis.ne.binarysupermarket.audits.TimestampAudit;
import rw.pacis.ne.binarysupermarket.dtos.CreateOrUpdateProductDTO;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product extends TimestampAudit {

    @Id
    @Column(length = 100)
    private String code;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Double price;

    @Column
    private String image;

    @Transient
    private Integer quantity;

    public Product(CreateOrUpdateProductDTO dto) {
        this.code = dto.getCode();
        this.name = dto.getName();
        this.type = dto.getType();
        this.price = dto.getPrice();
        this.image = dto.getImage();

    }
}
