package com.rgbaquamart.be.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rgbaquamart.be.domain.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

	Optional<Brand> findByName(String name);

	List<Brand> findByStatus(String status);

	Optional<Brand> findByIdAndName(String id, Long name);

	Optional<Brand> findByIdAndStatus(Long id, String status);

	Boolean existsByName(String name);

	Boolean existsByNameAndIdNotIn(String name, Long id);

}
