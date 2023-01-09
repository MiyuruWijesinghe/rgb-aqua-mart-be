package com.rgbaquamart.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rgbaquamart.be.domain.Brand;
import com.rgbaquamart.be.resource.BrandResource;


@Service
public interface BrandService {

	
	List<Brand> findAll();
	
	
	Optional<Brand> findById(Long id);
	
	
	Optional<Brand> findByName(String name);
	
	
	List<Brand> findByStatus(String status);
	
	
	Brand add(BrandResource brandResource);

	
	Brand update(Long id, BrandResource brandResource);
	
	
	String delete(Long id);
}
