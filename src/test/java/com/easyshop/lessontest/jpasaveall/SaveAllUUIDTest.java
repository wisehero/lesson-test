package com.easyshop.lessontest.jpasaveall;


import com.easyshop.lessontest.saveall.UUIDMerchant;
import com.easyshop.lessontest.saveall.UUIDMerchantJpaRepository;
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
public class SaveAllUUIDTest {

    @Autowired
    private UUIDMerchantJpaRepository uuidMerchantJpaRepository;

    @Autowired
    private DatabaseCleanUp databaseCleanUp;

    @AfterEach
    void cleanUp() {
        databaseCleanUp.truncateAllTables();
    }


    @Test
    @DisplayName("saveAllUUIDTest - saveAll 100개 저장")
    void saveAllTest_100() {
        List<UUIDMerchant> merchants = createUUIDMerchants(100);

        long startTime = System.currentTimeMillis();

        uuidMerchantJpaRepository.saveAll(merchants);

        long endTime = System.currentTimeMillis();
        System.out.println("UUID 100개 실행 시간: " + (endTime - startTime) + "ms");
    }

    @Test
    @DisplayName("saveAllUUIDTest - saveAll 1000개 저장")
    void saveAllTest_1000() {
        List<UUIDMerchant> merchants = createUUIDMerchants(1000);

        long startTime = System.currentTimeMillis();

        uuidMerchantJpaRepository.saveAll(merchants);

        long endTime = System.currentTimeMillis();
        System.out.println("UUID 1000개 실행 시간: " + (endTime - startTime) + "ms");
    }

    @Test
    @DisplayName("saveAllUUIDTest - saveAll 10000개 저장")
    void saveAllTest_10000() {
        List<UUIDMerchant> merchants = createUUIDMerchants(10000);

        long startTime = System.currentTimeMillis();

        uuidMerchantJpaRepository.saveAll(merchants);

        long endTime = System.currentTimeMillis();
        System.out.println("UUID 10000개 실행 시간: " + (endTime - startTime) + "ms");
    }


    private List<UUIDMerchant> createUUIDMerchants(int count) {
        return Instancio.ofList(UUIDMerchant.class)
                .size(count)
                .ignore(field(UUIDMerchant::getId))
                .generate(field(UUIDMerchant::getBusinessNumber), gen -> gen.string().length(10).digits())
                .generate(field(UUIDMerchant::getBusinessName), gen -> gen.string().length(5, 20))
                .generate(field(UUIDMerchant::getOwnerName), gen -> gen.string().length(3, 10))
                .generate(field(UUIDMerchant::getOwnerPhoneNo), gen -> gen.string().prefix("010").digits().length(11))
                .create();
    }
}
