package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	// Método para buscar todas las categorías.
	public ResponseEntity<CategoryResponseRest> search();
	
	// Método para buscar una categoría por su ID.
	public ResponseEntity<CategoryResponseRest> searchByID(Long id);
	
}
