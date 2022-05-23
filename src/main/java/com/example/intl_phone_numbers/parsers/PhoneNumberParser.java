package com.example.intl_phone_numbers.parsers;

import com.example.intl_phone_numbers.responsebodies.PhoneNumberResponse;
import com.example.intl_phone_numbers.responsebodies.PhoneNumberState;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

@Component
public class PhoneNumberParser{

    private final HashMap<String, Country> countryCodeToCountryMap = new HashMap<>();

    public PhoneNumberParser() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode countries = objectMapper.readTree(new ClassPathResource("countries (BE).json").getInputStream())
                .get("countries");

        Country country;
        for(JsonNode jsonNode : countries){
            country = objectMapper.convertValue(jsonNode, Country.class);
            countryCodeToCountryMap.put(country.getCountryCode(), country);
        }
    }

    public PhoneNumberResponse getPhoneNumberResponse(String fullPhoneNumber){
        String[] phoneNumberParts = fullPhoneNumber.split("\\) ?");
        String countryCode = phoneNumberParts[0].replace('(', '+');
        String phoneNumber = phoneNumberParts[1];

        Country country = countryCodeToCountryMap.getOrDefault(countryCode, new Country());

        PhoneNumberResponse phoneNumberResponse = new PhoneNumberResponse();
        phoneNumberResponse.setNumber(phoneNumber);
        phoneNumberResponse.setCountry(country.getName());
        phoneNumberResponse.setCountryCode(countryCode);

        if (Pattern.matches(country.getNumberPattern(), fullPhoneNumber))
            phoneNumberResponse.setState(PhoneNumberState.VALID);
        else
            phoneNumberResponse.setState(PhoneNumberState.INVALID);

        return phoneNumberResponse;
    }
}
