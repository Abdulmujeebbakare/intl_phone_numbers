package com.example.intl_phone_numbers.services;

import com.example.intl_phone_numbers.parsers.PhoneNumberParser;
import com.example.intl_phone_numbers.repositories.CustomerRepository;
import com.example.intl_phone_numbers.responsebodies.PaginatedResponse;
import com.example.intl_phone_numbers.responsebodies.PhoneNumberResponse;
import com.example.intl_phone_numbers.responsebodies.PhoneNumberState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final PhoneNumberParser phoneNumberParser;

    public PaginatedResponse<PhoneNumberResponse> getPhoneNumbers(
            String numberPattern, PhoneNumberState state, long page, long limit){

        List<String> phoneNumbers = customerRepository.getPhoneNumbersMatchingPattern(numberPattern);


        long totalItemsInDatabase;
        List<PhoneNumberResponse> phoneNumbersResponseList = new ArrayList<>(phoneNumbers.size());
        if (state == null){
            for (String phoneNumber : phoneNumbers) {
                phoneNumbersResponseList.add(phoneNumberParser.getPhoneNumberResponse(phoneNumber));
            }
            totalItemsInDatabase = phoneNumbers.size();
        }else {
            PhoneNumberResponse phoneNumberResponse;
            long tally = 0;
            for (String phoneNumber : phoneNumbers) {
                phoneNumberResponse = phoneNumberParser.getPhoneNumberResponse(phoneNumber);
                if (phoneNumberResponse.getState().equals(state)) {
                    tally++;
                    phoneNumbersResponseList.add(phoneNumberResponse);
                }
            }
            totalItemsInDatabase = tally;
        }


        //this piece of code controls pagination. We use it to calculate the sublist size
        int subListSize;
        long offset = (page - 1) * limit;
        int lastPage = (int)Math.ceil((double) totalItemsInDatabase / limit);
        if (totalItemsInDatabase > limit){
            if (page == lastPage){
                subListSize = (int) totalItemsInDatabase % (int) offset;
            }else {
                subListSize = (int) limit;
            }
        }else {
            subListSize = (int) totalItemsInDatabase;
        }

        phoneNumbersResponseList = phoneNumbersResponseList.subList((int) offset, (int) (offset + subListSize));


        PaginatedResponse<PhoneNumberResponse> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setPage(page);
        paginatedResponse.setItems(phoneNumbersResponseList);
        paginatedResponse.setLimit(limit);
        paginatedResponse.setOffset(offset);
        paginatedResponse.setTotalItems(totalItemsInDatabase);

        return paginatedResponse;
    }
}
