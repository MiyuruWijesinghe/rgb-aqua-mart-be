package com.rgbaquamart.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbaquamart.be.domain.UserIdentification;

@Repository
public interface UserIdentificationRepository extends JpaRepository<UserIdentification, Long> {

}
