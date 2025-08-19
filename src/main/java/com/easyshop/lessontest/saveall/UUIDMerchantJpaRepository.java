package com.easyshop.lessontest.saveall;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UUIDMerchantJpaRepository extends JpaRepository<UUIDMerchant, UUID> {
}
