package com.company.inventory.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {

	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

	//Getter y Setter
	public ArrayList<HashMap<String, String>> getMetadata() {
		return metadata;
	}

	public void setMetadata(String type, String code, String date) {
		// Se crea un nuevo HashMap para almacenar los metadatos.
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("code", code);
		map.put("date", date);
		
		metadata.add(map); //Se añade el mapa de metadatos a la lista de metadatos.
 	}
}
