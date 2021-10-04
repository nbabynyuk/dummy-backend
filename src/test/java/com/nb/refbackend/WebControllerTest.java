package com.nb.refbackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestPropertySource(properties = {
    "SAMPLE-APP-CONFIG-VALUE = config123",
    "SAMPLE-APP-SECRET-VALUE = value-for-integration-tests"
})
@WebMvcTest(controllers = WebController.class)
class WebControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void index() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("sampleSecretValue", is("value-for-integration-tests")))
            .andExpect(jsonPath("sampleConfigValue", is("config123")))
            .andReturn();
       
    }
}