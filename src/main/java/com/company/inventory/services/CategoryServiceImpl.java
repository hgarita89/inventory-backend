package com.company.inventory.services;

import java.util.List;

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
}
