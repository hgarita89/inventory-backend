package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;

@Service //Con esto se le indica que es una clase de servicio 
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired //Para instanciar y gestionar la siguiente dependencia .
	private ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true) //Para indicar que este metodo no modificara la base de datos.
	public ResponseEntity<CategoryResponseRest> search() {
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			// Se crea una lista de objetos Category a partir de los resultados de la consulta a la base de datos.
			List<Category> category = (List<Category>) categoryDao.findAll();
			
			// Se establece la lista de categorías en el objeto categoryResponse de la respuesta.
			response.getCategoryResponse().setCategory(category);
			
			// Se establecen los metadatos de la respuesta.
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true) //Para indicar que este metodo no modificara la base de datos.
	public ResponseEntity<CategoryResponseRest> searchByID(Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Optional<Category> category = categoryDao.findById(id);
			if (category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta ok", "00", "Categoria encontrada");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	//Para guardar una categoria
	@Override
	@Transactional //Para indicar que este metodo modificara la base de datos.
	public ResponseEntity<CategoryResponseRest> save(Category category) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Category categorySaved = categoryDao.save(category);
			//Validacion
			if(categorySaved != null) {
				list.add(categorySaved);
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta ok", "00", "Categoria guardada");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Categoria no guardada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al guardar categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	
	@Override
	@Transactional //Para indicar que este metodo modificara la base de datos.
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		try {
			Optional<Category> categorySearch = categoryDao.findById(id);
			if (categorySearch.isPresent()) {
				//Se procedera a actualizar el registro
				categorySearch.get().setName(category.getName());
				categorySearch.get().setDescription(category.getDescription());
				
				Category categoryToUpdate = categoryDao.save(categorySearch.get());
				
				//Validacion 
				if (categoryToUpdate != null) {
					list.add(categoryToUpdate);
					response.getCategoryResponse().setCategory(list);
					response.setMetadata("Respuesta ok", "00", "Categoria actualizada");
				} else {
					response.setMetadata("Respuesta nok", "-1", "Categoria no actualizada");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}
}
