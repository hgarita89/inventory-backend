package com.company.inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.inventory.model.Category;

//Para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en entidades en una base de datos relacional.
public interface ICategoryDao extends CrudRepository<Category, Long>{

}
