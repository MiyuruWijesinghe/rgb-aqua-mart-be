package com.rgbaquamart.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbaquamart.be.domain.SpecialInstructions;

@Repository
public interface SpecialInstructionsRepository extends JpaRepository<SpecialInstructions, Long> {

}
