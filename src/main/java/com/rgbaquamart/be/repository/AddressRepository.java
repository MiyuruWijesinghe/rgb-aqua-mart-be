package com.rgbaquamart.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbaquamart.be.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
