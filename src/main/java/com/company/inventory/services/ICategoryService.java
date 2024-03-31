package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;

public interface ICategoryService {
	
	// Método para buscar todas las categorías.
	public ResponseEntity<CategoryResponseRest> search();
	
	// Método para buscar una categoría por su ID.
	public ResponseEntity<CategoryResponseRest> searchByID(Long id);
	
	// Método para guardar una categoría.
	public ResponseEntity<CategoryResponseRest> save(Category category);
	
	// Método para actualizar una categoría por su ID.
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	
	// Método para eliminar una categoría por su ID.
	public ResponseEntity<CategoryResponseRest> deletById(Long id);
}
