package com.rgbaquamart.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rgbaquamart.be.domain.Category;
import com.rgbaquamart.be.resource.CategoryResource;

@Service
public interface CategoryService {

	List<Category> findAll();
	
	
	Optional<Category> findById(Long id);
	
	
	Optional<Category> findByName(String name);
	
	
	List<Category> findByStatus(String status);
	
	
	Category add(CategoryResource categoryResource);

	
	Category update(Long id, CategoryResource categoryResource);
	
	
	String delete(Long id);
	
}
