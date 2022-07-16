package com.example.Precept.Backend.APIs.modeller.Partner;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(
        name = "partner"
)

@Getter
@Setter
@ToString
public class Partner {
    @Id

    @SequenceGenerator(
            name = "partner_sequence_name",
            sequenceName = "partner_sequence_name",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "partner_sequence_name"
    )

    @Column(
            name = "id",
            updatable = false
    )
    private long ID;
    private String partnerName;


    public Partner(String partnerName) {
        this.partnerName = partnerName;

    }

    public Partner() {
    }


    public Partner(long ID, String partnerName) {
        this.ID = ID;
        this.partnerName = partnerName;
    }

}
