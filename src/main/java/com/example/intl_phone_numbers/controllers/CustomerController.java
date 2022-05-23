package com.example.intl_phone_numbers.controllers;

import com.example.intl_phone_numbers.responsebodies.PaginatedResponse;
import com.example.intl_phone_numbers.responsebodies.PhoneNumberResponse;
import com.example.intl_phone_numbers.responsebodies.PhoneNumberState;
import com.example.intl_phone_numbers.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("phone_numbers")
    public PaginatedResponse<PhoneNumberResponse> getCustomersPhoneNumbers(
            @RequestParam(required = false, defaultValue = "%") String phoneNumber,
            @RequestParam(required = false, defaultValue = "%") String countryCode,
            @RequestParam(required = false) String state,
            @RequestParam(defaultValue = "10", required = false) String limit,
            @RequestParam(defaultValue = "1", required = false) String page){

        long limitValue, pageValue;

        limit = limit.trim();
        if (Pattern.matches("\\d+", limit)){
            limitValue = Long.parseLong(limit);
        }else {
            limitValue = 10;
        }


        page = page.trim();
        if (Pattern.matches("\\d+", page)){
            pageValue = Long.parseLong(page);
        }else {
            pageValue = 1;
        }


        String numberPattern;
        countryCode = countryCode.trim();
        phoneNumber = phoneNumber.trim();
        if(Pattern.matches("\\d{3,4}", countryCode)){
            numberPattern = "(" + countryCode + ")%" + phoneNumber + "%";
        }else {
            numberPattern = "%" + phoneNumber + "%";
        }

        PhoneNumberState phoneNumberState = null;
        if (state != null){
            try {
                phoneNumberState = PhoneNumberState.valueOf(state.trim().toUpperCase());
            }catch (IllegalArgumentException ignored){}
        }

        return customerService.getPhoneNumbers(numberPattern, phoneNumberState, pageValue, limitValue);
    }
}
