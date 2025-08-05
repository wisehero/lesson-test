package com.easyshop.lessontest.jpasaveall;


import com.easyshop.lessontest.saveall.Merchant;
import com.easyshop.lessontest.saveall.MerchantJpaRepository;
import com.easyshop.lessontest.support.DatabaseCleanUp;
import com.easyshop.lessontest.support.MySqlTestContainersConfig;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.instancio.Select.field;

@SpringBootTest
@Import(MySqlTestContainersConfig.class)
public class SaveAllTest {

    @Autowired
    private MerchantJpaRepository merchantJpaRepository;


    @Autowired
    private DatabaseCleanUp databaseCleanUp;

    @AfterEach
    void cleanUp() {
        databaseCleanUp.truncateAllTables();
    }

    @Test
    @DisplayName("saveAllTest - saveAll 100개 저장")
    void saveAllTest_100() {
        List<Merchant> merchants = createMerchants(100);

        long startTime = System.currentTimeMillis();

        merchantJpaRepository.saveAll(merchants);

        long endTime = System.currentTimeMillis();
        System.out.println("실행 시간: " + (endTime - startTime) + "ms");
    }

    @Test
    @DisplayName("saveAllTest - saveAll 1000개 저장")
    void saveAllTest_1000() {
        List<Merchant> merchants = createMerchants(1000);

        long startTime = System.currentTimeMillis();

        merchantJpaRepository.saveAll(merchants);

        long endTime = System.currentTimeMillis();
        System.out.println("실행 시간: " + (endTime - startTime) + "ms");
    }

    @Test
    @DisplayName("saveAllTest - saveAll 10000개 저장")
    void saveAllTest_10000() {
        List<Merchant> merchants = createMerchants(10000);

        long startTime = System.currentTimeMillis();

        merchantJpaRepository.saveAll(merchants);

        long endTime = System.currentTimeMillis();
        System.out.println("실행 시간: " + (endTime - startTime) + "ms");
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
