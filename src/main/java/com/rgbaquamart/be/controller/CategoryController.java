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
import com.rgbaquamart.be.domain.Category;
import com.rgbaquamart.be.exceptions.UserNotFound;
import com.rgbaquamart.be.resource.CategoryResource;
import com.rgbaquamart.be.resource.MessageResponseResource;
import com.rgbaquamart.be.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllCategories() {
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Category> isPresentCategory = categoryService.findAll();
		if (!isPresentCategory.isEmpty()) {
			return new ResponseEntity<>((Collection<Category>) isPresentCategory, HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getCategoryById(@PathVariable(value = "id", required = true) Long id) {
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Category> isPresentCategory = categoryService.findById(id);
		if (isPresentCategory.isPresent()) {
			return new ResponseEntity<>(isPresentCategory.get(), HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getCategoryByName(@PathVariable(value = "name", required = true) String name) {
		MessageResponseResource responseMessage = new MessageResponseResource();
		Optional<Category> isPresentCategory = categoryService.findByName(name);
		if (isPresentCategory.isPresent()) {
			return new ResponseEntity<>(isPresentCategory.get(), HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getCategoryByStatus(@PathVariable(value = "status", required = true) String status) {
		MessageResponseResource responseMessage = new MessageResponseResource();
		List<Category> isPresentCategory = categoryService.findByStatus(status);
		if (!isPresentCategory.isEmpty()) {
			return new ResponseEntity<>((Collection<Category>) isPresentCategory, HttpStatus.OK);
		} else {
			responseMessage.setMessage(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addCategory(@Valid @RequestBody CategoryResource categoryResource) {
		if (LogginAuthentcation.getUserName() == null || LogginAuthentcation.getUserName().isEmpty())
			throw new UserNotFound(environment.getProperty("common.user-not-found"));
		Category category = categoryService.add(categoryResource);
		MessageResponseResource messageResponseResource = new MessageResponseResource(environment.getProperty("common.saved"), category);
		return new ResponseEntity<>(messageResponseResource, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable(value = "id", required = true) Long id,
											  @Valid @RequestBody CategoryResource categoryResource) {
		if (LogginAuthentcation.getUserName() == null || LogginAuthentcation.getUserName().isEmpty())
			throw new UserNotFound(environment.getProperty("common.user-not-found"));
		Category category = categoryService.update(id, categoryResource);
		MessageResponseResource messageResponseResource = new MessageResponseResource(environment.getProperty("common.updated"), category);
		return new ResponseEntity<>(messageResponseResource, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id", required = true) Long id) {
		String message = categoryService.delete(id);
		MessageResponseResource messageResponseResource = new MessageResponseResource(message);
		return new ResponseEntity<>(messageResponseResource, HttpStatus.OK);
	}
	
}
