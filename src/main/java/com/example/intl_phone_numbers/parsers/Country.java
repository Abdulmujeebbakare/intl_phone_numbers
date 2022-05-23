package com.example.intl_phone_numbers.parsers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Country {

    private String name = "UNKNOWN COUNTRY";

    private String countryCode = "UNKNOWN CODE";

    private String numberPattern = ".+";

    @Override
    public int hashCode() {
        return Integer.parseInt(countryCode.replace("+",""));
    }
}
