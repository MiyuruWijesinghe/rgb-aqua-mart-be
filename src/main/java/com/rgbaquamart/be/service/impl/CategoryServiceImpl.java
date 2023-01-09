package com.rgbaquamart.be.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rgbaquamart.be.core.LogginAuthentcation;
import com.rgbaquamart.be.domain.Category;
import com.rgbaquamart.be.exceptions.ValidateRecordException;
import com.rgbaquamart.be.repository.CategoryRepository;
import com.rgbaquamart.be.resource.CategoryResource;
import com.rgbaquamart.be.service.CategoryService;
import com.rgbaquamart.be.service.CommonMethodsService;

@Component
@Transactional(rollbackFor=Exception.class)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CommonMethodsService commonMethodsService;
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findById(Long id) {
		Optional<Category> isPresentCategory = categoryRepository.findById(id);
		if (isPresentCategory.isPresent()) {
			return Optional.ofNullable(isPresentCategory.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Category> findByName(String name) {
		Optional<Category> isPresentCategory = categoryRepository.findByName(name);
		if (isPresentCategory.isPresent()) {
			return Optional.ofNullable(isPresentCategory.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<Category> findByStatus(String status) {
		return categoryRepository.findByStatus(status);
	}

	@Override
	public Category add(CategoryResource categoryResource) {
		if(categoryRepository.existsByName(categoryResource.getName())) {
        	throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
        }
        
		Category category = new Category();
		category.setName(categoryResource.getName());
		category.setDescription(categoryResource.getDescription());
		category.setLogoURL(categoryResource.getLogoURL());
		category.setStatus(categoryResource.getStatus());
		category.setCreatedDate(commonMethodsService.getCreateOrModifyDate());
		category.setCreatedUser(LogginAuthentcation.getUserName());
		category = categoryRepository.save(category);
    	return category;
	}

	@Override
	public Category update(Long id, CategoryResource categoryResource) {
		Optional<Category> isPresentCategory = categoryRepository.findById(id);
		if (!isPresentCategory.isPresent()) 
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		
		if(categoryRepository.existsByNameAndIdNotIn(categoryResource.getName(), id)) {
        	throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
        }
		
		Category category = isPresentCategory.get();
		category.setName(categoryResource.getName());
		category.setDescription(categoryResource.getDescription());
		category.setLogoURL(categoryResource.getLogoURL());
		category.setStatus(categoryResource.getStatus());
		category.setModifiedDate(commonMethodsService.getCreateOrModifyDate());
		category.setModifiedUser(LogginAuthentcation.getUserName());
		category = categoryRepository.saveAndFlush(category);
    	return category;
	}

	@Override
	public String delete(Long id) {
		Optional<Category> isPresentCategory = categoryRepository.findById(id);
		if (!isPresentCategory.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		}	
		categoryRepository.deleteById(id);
		return environment.getProperty("common.deleted-id") + id;
	}

}
