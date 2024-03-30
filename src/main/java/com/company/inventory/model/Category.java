package com.company.inventory.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//@Entity es una anotación de JPA que se utiliza para indicar que una clase es una entidad. 
@Entity 

//@Table es otra anotación de JPA que se utiliza para especificar el nombre de la tabla a la que está mapeada la entidad.
@Table(name="category")

/**Data genera todo el código repetitivo de las clases: constructor, getters 
para todos los campos y setters para todos los campos no finales */
@Data 

public class Category implements Serializable{
	/**La interfaz Serializable se utiliza cuando necesitas enviar un objeto a través 
	 * de la red o guardarlo en un archivo o base de datos para su uso posterior */
	private static final long serialVersionUID = -4310027227752446841L;
	
	//Campos de la clase
	@Id //Para indicarle que el campo id sera la clave primaria en la tabla de base de datos
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Para que junto con @Id especificar que la clave primaria se genera automáticamente.
	private Long id;
	private String name;
	private String description;
}
