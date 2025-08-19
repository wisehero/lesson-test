package com.easyshop.lessontest.jpasaveall;

import com.easyshop.lessontest.saveall.Merchant;
import com.easyshop.lessontest.saveall.MerchantBulkInsertRepository;
import com.easyshop.lessontest.support.MySqlTestContainersConfig;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.instancio.Select.field;

@SpringBootTest
@Import(MySqlTestContainersConfig.class)
class JdbcBulkInsertTest {

    @Autowired
    private MerchantBulkInsertRepository merchantBulkInsertRepository;

    @Test
    @DisplayName("JdbcBulkInsertRepository를 사용하여 100개 삽입")
    void insert100Merchants() {

        List<Merchant> merchants = createMerchants(100);

        long startTime = System.currentTimeMillis();

        merchantBulkInsertRepository.saveAll(merchants);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("100개 Merchant 삽입 소요 시간: " + duration + "ms");
    }

    @Test
    @DisplayName("JdbcBulkInsertRepository를 사용하여 1000개 삽입")
    void insert1000Merchants() {

        List<Merchant> merchants = createMerchants(1000);

        long startTime = System.currentTimeMillis();

        merchantBulkInsertRepository.saveAll(merchants);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("1000개 Merchant 삽입 소요 시간: " + duration + "ms");
    }

    @Test
    @DisplayName("JdbcBulkInsertRepository를 사용하여 10000개 삽입")
    void insert10000Merchants() {

        List<Merchant> merchants = createMerchants(10000);

        long startTime = System.currentTimeMillis();

        merchantBulkInsertRepository.saveAll(merchants);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("10000개 Merchant 삽입 소요 시간: " + duration + "ms");
    }



    private List<Merchant> createMerchants(int count) {
        return Instancio.ofList(Merchant.class)
                .size(count)
                .ignore(field(Merchant::getId))
                .generate(field(Merchant::getBusinessNumber), gen -> gen.string().length(10).digits())
                .generate(field(Merchant::getBusinessName), gen -> gen.string().length(5, 20))
                .generate(field(Merchant::getOwnerName), gen -> gen.string().length(3, 10))
                .generate(field(Merchant::getOwnerPhoneNo), gen -> gen.string().prefix("010").digits().length(11))
                .create();
    }
}
