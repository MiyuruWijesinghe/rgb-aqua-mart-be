package com.rgbaquamart.be.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgbaquamart.be.core.LogginAuthentcation;
import com.rgbaquamart.be.domain.Brand;
import com.rgbaquamart.be.exceptions.UserNotFound;
import com.rgbaquamart.be.resource.BrandResource;
import com.rgbaquamart.be.resource.MessageResponseResource;
import com.rgbaquamart.be.service.BrandService;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin(origins = "*")
public class BrandController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BrandService brandService;
	
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllBrands() {
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Brand> isPresentBrand = brandService.findAll();
		if (!isPresentBrand.isEmpty()) {
			return new ResponseEntity<>((Collection<Brand>) isPresentBrand, HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getBrandById(@PathVariable(value = "id", required = true) Long id) {
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Brand> isPresentBrand = brandService.findById(id);
		if (isPresentBrand.isPresent()) {
			return new ResponseEntity<>(isPresentBrand.get(), HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getBrandByName(@PathVariable(value = "name", required = true) String name) {
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Brand> isPresentBrand = brandService.findByName(name);
		if (isPresentBrand.isPresent()) {
			return new ResponseEntity<>(isPresentBrand.get(), HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getBrandByStatus(@PathVariable(value = "status", required = true) String status) {
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Brand> isPresentBrand = brandService.findByStatus(status);
		if (!isPresentBrand.isEmpty()) {
			return new ResponseEntity<>((Collection<Brand>) isPresentBrand, HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addBrand(@Valid @RequestBody BrandResource brandResource) {
		if (LogginAuthentcation.getUserName() == null || LogginAuthentcation.getUserName().isEmpty())
			throw new UserNotFound(environment.getProperty("common.user-not-found"));
		Brand brand = brandService.add(brandResource);
		MessageResponseResource messageResponseResource = new MessageResponseResource(environment.getProperty("common.saved"), brand);
		return new ResponseEntity<>(messageResponseResource, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateBrand(@PathVariable(value = "id", required = true) Long id,
											  @Valid @RequestBody BrandResource brandResource) {
		if (LogginAuthentcation.getUserName() == null || LogginAuthentcation.getUserName().isEmpty())
			throw new UserNotFound(environment.getProperty("common.user-not-found"));
		Brand brand = brandService.update(id, brandResource);
		MessageResponseResource messageResponseResource = new MessageResponseResource(environment.getProperty("common.updated"), brand);
		return new ResponseEntity<>(messageResponseResource, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteBrand(@PathVariable(value = "id", required = true) Long id) {
		String message = brandService.delete(id);
		MessageResponseResource messageResponseResource = new MessageResponseResource(message);
		return new ResponseEntity<>(messageResponseResource, HttpStatus.OK);
	}
	
}
