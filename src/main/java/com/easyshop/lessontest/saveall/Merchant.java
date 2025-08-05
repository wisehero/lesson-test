package com.easyshop.lessontest.saveall;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "merchant")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessNumber;
    private String businessName;
    private String ownerName;
    private String ownerPhoneNo;

    public Merchant(String businessNumber, String businessName, String ownerName, String ownerPhoneNo) {
        this.businessNumber = businessNumber;
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.ownerPhoneNo = ownerPhoneNo;
    }
}
