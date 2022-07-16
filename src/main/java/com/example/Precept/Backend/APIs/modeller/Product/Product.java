package com.example.Precept.Backend.APIs.modeller.Product;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(
        name = "product"
)

@Getter
@Setter
@ToString
public class Product {
    @Id

    @SequenceGenerator(
            name = "product_sequence_name",
            sequenceName = "product_sequence_name",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence_name"
    )

    private long productID;
    private String productName;
    private String partner;
    private String commercial;

    public Product() {}

    public Product(long productID, String productName, String partner, String commercial) {
        this.productID = productID;
        this.productName = productName;
        this.partner = partner;
        this.commercial = commercial;
    }

    public Product(String productName, String partner, String commercial) {
        this.productName = productName;
        this.partner = partner;
        this.commercial = commercial;
    }
}
