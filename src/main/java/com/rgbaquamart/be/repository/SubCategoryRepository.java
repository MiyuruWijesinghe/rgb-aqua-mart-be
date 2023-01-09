package com.rgbaquamart.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbaquamart.be.domain.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
