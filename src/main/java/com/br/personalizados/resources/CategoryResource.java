package com.br.personalizados.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.personalizados.DTO.CategoryDTO;
import com.br.personalizados.model.Category;
import com.br.personalizados.services.CategoryService;
import com.br.personalizados.services.exceptions.ObjectNotFoundException;


@RestController
@RequestMapping(value="/categoria")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody CategoryDTO objDTO) {
		
		Category savedObj = service.save(service.fromDTO(objDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> getById(@PathVariable Integer id) {
			Category obj = service.find(id);
			return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO obj, @PathVariable Integer id){
		obj.setId(id);
		
		try {
			
			service.update(service.fromDTO(obj));
			
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.noContent().build();
	}
	
}
