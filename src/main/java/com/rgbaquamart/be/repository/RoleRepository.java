package com.rgbaquamart.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbaquamart.be.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
