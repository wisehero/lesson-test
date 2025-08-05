package com.easyshop.lessontest.saveall;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "merchant_uuid")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UUIDMerchant {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String businessNumber;
    private String businessName;
    private String ownerName;
    private String ownerPhoneNo;

    public UUIDMerchant(String businessNumber, String businessName, String ownerName, String ownerPhoneNo) {
        this.businessNumber = businessNumber;
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.ownerPhoneNo = ownerPhoneNo;
    }
}
