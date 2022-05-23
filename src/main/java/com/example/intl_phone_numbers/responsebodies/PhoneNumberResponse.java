package com.example.intl_phone_numbers.responsebodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberResponse {

    private String country;

    private String countryCode;

    private String number;

    private PhoneNumberState state;
}

