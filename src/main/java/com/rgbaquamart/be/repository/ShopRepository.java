package com.rgbaquamart.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbaquamart.be.domain.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
