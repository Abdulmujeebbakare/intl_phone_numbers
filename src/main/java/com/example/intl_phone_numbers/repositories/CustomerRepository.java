package com.example.intl_phone_numbers.repositories;

import com.example.intl_phone_numbers.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT phone FROM customer WHERE phone LIKE :code", nativeQuery = true)
    List<String> getPhoneNumbersMatchingPattern(@Param("code") String code);
}
