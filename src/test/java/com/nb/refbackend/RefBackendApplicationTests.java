package com.nb.refbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "SAMPLE-APP-CONFIG-VALUE = config123",
    "SAMPLE-APP-SECRET-VALUE = value-for-integration-tests"
})
class RefBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
