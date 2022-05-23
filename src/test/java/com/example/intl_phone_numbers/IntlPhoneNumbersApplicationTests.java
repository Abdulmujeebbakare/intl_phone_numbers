package com.example.intl_phone_numbers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest
@AutoConfigureMockMvc
class IntlPhoneNumbersApplicationTests {

    private final Logger logger = Logger.getLogger("TestLogger");

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPhoneNumbersWhenNoParametersAreSupplied() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/phone_numbers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> logger.log(Level.INFO, result.getResponse().getContentAsString()));
    }

    @Test
    void getPhoneNumbersWhenPageIsSupplied() throws Exception{
        int page = 2;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/phone_numbers?page=" + page))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> logger.log(Level.INFO, result.getResponse().getContentAsString()));
    }

    @Test
    void getPhoneNumbersWhenPhoneNumberAndCountryCodeAreSupplied() throws Exception{
        String number = "69";
        String code = "237";

        StringBuilder requestStringBuilder = new StringBuilder("/api/customers/phone_numbers");
        requestStringBuilder.append("?");
        requestStringBuilder.append("phoneNumber=").append(number);
        requestStringBuilder.append("&");
        requestStringBuilder.append("countryCode=").append(code);

        mockMvc.perform(MockMvcRequestBuilders.get(requestStringBuilder.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> logger.log(Level.INFO, result.getResponse().getContentAsString()));
    }

    @Test
    void getPhoneNumbersWhenStateIsSupplied() throws Exception{
        //String state = "VALID";
        String state = "INVALID";

        StringBuilder requestStringBuilder = new StringBuilder("/api/customers/phone_numbers");
        requestStringBuilder.append("?");
        requestStringBuilder.append("state=").append(state);

        mockMvc.perform(MockMvcRequestBuilders.get(requestStringBuilder.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> logger.log(Level.INFO, result.getResponse().getContentAsString()));
    }

    @Test
    void getPhoneNumbersWhenAllRequestParamsAreSupplied() throws Exception{
        int page = 1;
        String number = "7";
        String code = "237";
        String state = "INVALID";

        StringBuilder requestStringBuilder = new StringBuilder("/api/customers/phone_numbers");
        requestStringBuilder.append("?");
        requestStringBuilder.append("phoneNumber=").append(number);
        requestStringBuilder.append("&");
        requestStringBuilder.append("countryCode=").append(code);
        requestStringBuilder.append("&");
        requestStringBuilder.append("page=").append(page);
        requestStringBuilder.append("&");
        requestStringBuilder.append("state=").append(state);

        mockMvc.perform(MockMvcRequestBuilders.get(requestStringBuilder.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> logger.log(Level.INFO, result.getResponse().getContentAsString()));
    }

}
