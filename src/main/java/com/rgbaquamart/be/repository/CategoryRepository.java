package com.rgbaquamart.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rgbaquamart.be.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByName(String name);

	List<Category> findByStatus(String status);

	Optional<Category> findByIdAndName(String id, Long name);

	Optional<Category> findByIdAndStatus(Long id, String status);

	Boolean existsByName(String name);

	Boolean existsByNameAndIdNotIn(String name, Long id);
}
