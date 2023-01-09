package com.rgbaquamart.be.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rgbaquamart.be.core.LogginAuthentcation;
import com.rgbaquamart.be.domain.Brand;
import com.rgbaquamart.be.exceptions.ValidateRecordException;
import com.rgbaquamart.be.repository.BrandRepository;
import com.rgbaquamart.be.resource.BrandResource;
import com.rgbaquamart.be.service.BrandService;
import com.rgbaquamart.be.service.CommonMethodsService;

@Component
@Transactional(rollbackFor=Exception.class)
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CommonMethodsService commonMethodsService;
	 
	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public Optional<Brand> findById(Long id) {
		Optional<Brand> isPresentBrand = brandRepository.findById(id);
		if (isPresentBrand.isPresent()) {
			return Optional.ofNullable(isPresentBrand.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Brand> findByName(String name) {
		Optional<Brand> isPresentBrand = brandRepository.findByName(name);
		if (isPresentBrand.isPresent()) {
			return Optional.ofNullable(isPresentBrand.get());
		}
		else {
			return Optional.empty();
		}
	}

	@Override
	public List<Brand> findByStatus(String status) {
		return brandRepository.findByStatus(status);
	}

	@Override
	public Brand add(BrandResource brandResource) {
        if(brandRepository.existsByName(brandResource.getName())) {
        	throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
        }
        
        Brand brand = new Brand();
        brand.setName(brandResource.getName());
        brand.setDescription(brandResource.getDescription());
        brand.setLogoURL(brandResource.getLogoURL());
        brand.setStatus(brandResource.getStatus());
        brand.setCreatedDate(commonMethodsService.getCreateOrModifyDate());
        brand.setCreatedUser(LogginAuthentcation.getUserName());
        brand = brandRepository.save(brand);
    	return brand;
	}

	@Override
	public Brand update(Long id, BrandResource brandResource) {
		Optional<Brand> isPresentBrand = brandRepository.findById(id);
		if (!isPresentBrand.isPresent()) 
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		
		if(brandRepository.existsByNameAndIdNotIn(brandResource.getName(), id)) {
        	throw new ValidateRecordException(environment.getProperty("common.duplicate"), "name");
        }
		
		Brand brand = isPresentBrand.get();
		brand.setName(brandResource.getName());
		brand.setDescription(brandResource.getDescription());
	    brand.setLogoURL(brandResource.getLogoURL());
		brand.setStatus(brandResource.getStatus());
		brand.setModifiedDate(commonMethodsService.getCreateOrModifyDate());
		brand.setModifiedUser(LogginAuthentcation.getUserName());
		brand = brandRepository.saveAndFlush(brand);
    	return brand;
	}

	@Override
	public String delete(Long id) {
		Optional<Brand> isPresentBrand = brandRepository.findById(id);
		if (!isPresentBrand.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("common.record-not-found"), "message");
		}	
		brandRepository.deleteById(id);
		return environment.getProperty("common.deleted-id") + id;
	}
	
}
