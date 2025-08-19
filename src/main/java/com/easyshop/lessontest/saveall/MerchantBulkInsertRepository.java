package com.easyshop.lessontest.saveall;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MerchantBulkInsertRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final int BATCH_SIZE = 1000;

   @Transactional
    public void saveAll(List<Merchant> merchants) {
        String sql = """
                INSERT INTO merchant (business_number, business_name, owner_name, owner_phone_no)
                VALUES (?, ?, ?, ?)
                """;

        jdbcTemplate.batchUpdate(sql, merchants, BATCH_SIZE,
                // PreparedStatementSetter 인터페이스를 람다식으로 구현
                (PreparedStatement ps, Merchant merchant) -> {
                    int index = 1;
                    ps.setString(index++, merchant.getBusinessNumber());
                    ps.setString(index++, merchant.getBusinessName());
                    ps.setString(index++, merchant.getOwnerName());
                    ps.setString(index, merchant.getOwnerPhoneNo());
                }
        );
    }
}
