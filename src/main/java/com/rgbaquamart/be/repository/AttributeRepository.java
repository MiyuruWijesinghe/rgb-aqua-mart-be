package com.rgbaquamart.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbaquamart.be.domain.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}
