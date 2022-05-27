package com.example.myproject.repository;

import com.example.myproject.domain.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

}