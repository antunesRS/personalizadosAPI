package com.br.personalizados.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.personalizados.model.Category;
import com.br.personalizados.services.CategoryService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categoria")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Category obj) {
		
		Category savedObj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		try {
			Category obj = service.find(id);
			return ResponseEntity.ok().body(obj);
			
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
